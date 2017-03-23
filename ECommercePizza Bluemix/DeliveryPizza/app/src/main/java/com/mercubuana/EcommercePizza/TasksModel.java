package com.ibm.cloudantstarter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.cloudant.sync.datastore.ConflictException;
import com.cloudant.sync.datastore.Datastore;
import com.cloudant.sync.datastore.DatastoreManager;
import com.cloudant.sync.datastore.DatastoreNotCreatedException;
import com.cloudant.sync.datastore.DocumentBodyFactory;
import com.cloudant.sync.datastore.DocumentException;
import com.cloudant.sync.datastore.DocumentRevision;
import com.cloudant.sync.event.Subscribe;
import com.cloudant.sync.notifications.ReplicationCompleted;
import com.cloudant.sync.notifications.ReplicationErrored;
import com.cloudant.sync.replication.Replicator;
import com.cloudant.sync.replication.ReplicatorBuilder;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles creating the Cloudant datastore(s) and remote replication.
 * This is where the bulk of the Cloudant store code lives.
 * Shows you how to create, replicate, and perform all CRUD operations across remote and local Cloudant datastores.
 */
class TasksModel {

    private static final String LOG_TAG = "TasksModel";

    private static final String DATASTORE_MANGER_DIR = "data";
    private static final String TASKS_DATASTORE_NAME = "tasks";

    // Local datastore to CRUD tasks and update with remote Bluemix datastore.
    private Datastore mDatastore;

    // Replicators used to Push and/or pull data to and/or from remote datastore on Bluemix.
    private Replicator mPushReplicator;
    private Replicator mPullReplicator;

    private final Context mContext;
    private final Handler mHandler;
    private MainActivity mListener;

    TasksModel(Context context) {

        this.mContext = context;

        // Set up our tasks local Cloudant datastore within its own folder in the application's
        // data directory.
        File path = this.mContext.getApplicationContext().getDir(
                DATASTORE_MANGER_DIR,
                Context.MODE_PRIVATE
        );
        DatastoreManager manager = DatastoreManager.getInstance(path.getAbsolutePath());
        try {
            this.mDatastore = manager.openDatastore(TASKS_DATASTORE_NAME);
        } catch (DatastoreNotCreatedException dnce) {
            Log.e(LOG_TAG, "Unable to open Datastore", dnce);
        }

        Log.d(LOG_TAG, "Set up database at " + path.getAbsolutePath());

        // Set up the replicator objects from the app's settings.
        try {
            this.loadReplicationSettings();
        } catch (URISyntaxException e) {
            Log.e(LOG_TAG, "Unable to construct remote URI from configuration", e);
        }

        // Allow us to switch code called by the ReplicationListener into
        // the main thread so the UI can update safely.
        this.mHandler = new Handler(Looper.getMainLooper());

        Log.d(LOG_TAG, "TasksModel set up " + path.getAbsolutePath());
    }

    /**
     * Sets the listener for replication callbacks as a weak reference.
     * @param listener {@link MainActivity} to receive callbacks.
     */
    void setReplicationListener(MainActivity listener) {
        this.mListener = listener;
    }

    /**
     * Creates a task, assigning an ID.
     * @param task task to create.
     * @return new revision of the document.
     */
    Task createDocument(Task task) {
        DocumentRevision rev = new DocumentRevision();
        rev.setBody(DocumentBodyFactory.create(task.asMap()));
        try {
            DocumentRevision created = this.mDatastore.createDocumentFromRevision(rev);
            return Task.fromRevision(created);
        } catch (DocumentException de) {
            return null;
        }
    }

    /**
     * Updates a Task document within the datastore.
     * @param task task to update.
     * @return the updated revision of the Task.
     * @throws ConflictException if the task passed in has a rev which doesn't
     *      match the current rev in the datastore.
     */
    Task updateDocument(Task task) throws ConflictException {
        DocumentRevision rev = task.getDocumentRevision();
        rev.setBody(DocumentBodyFactory.create(task.asMap()));
        try {
            DocumentRevision updated = this.mDatastore.updateDocumentFromRevision(rev);
            return Task.fromRevision(updated);
        } catch (DocumentException de) {
            return null;
        }
    }

    /**
     * Deletes a Task document within the datastore.
     * @param task task to delete
     * @throws ConflictException if the task passed in has a rev which doesn't
     *      match the current rev in the datastore.
     */
    void deleteDocument(Task task) throws ConflictException {
        this.mDatastore.deleteDocumentFromRevision(task.getDocumentRevision());
    }

    /**
     * Returns all {@code Task} documents in the datastore.
     * @return a List<Task> object of size taskCount.
     */
    List<Task> allTasks() {
        int nDocs = this.mDatastore.getDocumentCount();
        List<DocumentRevision> all = this.mDatastore.getAllDocuments(0, nDocs, true);
        List<Task> tasks = new ArrayList<>();

        // Filter all documents down to those of type Task.
        for(DocumentRevision rev : all) {
            Task t = Task.fromRevision(rev);
            if (t != null) {
                tasks.add(t);
            }
        }

        return tasks;
    }

    /**
     * Stops running replications.
     *
     * The stop() methods stops the replications asynchronously, see the
     * replicator docs for more information.
     */
    void stopAllReplications() {
        if (this.mPullReplicator != null) {
            this.mPullReplicator.stop();
        }
        if (this.mPushReplicator != null) {
            this.mPushReplicator.stop();
        }
    }

    /**
     * Starts the configured push replication to Cloudant remote datastore on Bluemix.
     */
    void startPushReplication() {
        if (this.mPushReplicator != null) {
            this.mPushReplicator.start();
        } else {
            throw new RuntimeException("Push replication not set up correctly");
        }
    }

    /**
     * Starts the configured pull replication from Cloudant remote datastore on Bluemix.
     */
    void startPullReplication() {
        if (this.mPullReplicator != null) {
            this.mPullReplicator.start();
        } else {
            throw new RuntimeException("Push replication not set up correctly");
        }
    }

    /**
     * Loads the replication settings from cloudant_credentials.xml.
     */
    private void loadReplicationSettings() throws URISyntaxException {

        // Set up the replicator objects.
        URI uri = this.createServerURI();

        mPullReplicator = ReplicatorBuilder.pull().to(mDatastore).from(uri).build();
        mPushReplicator = ReplicatorBuilder.push().from(mDatastore).to(uri).build();

        // Registers this class to listen to replication events, complete and error functions below.
        mPushReplicator.getEventBus().register(this);
        mPullReplicator.getEventBus().register(this);

        Log.d(LOG_TAG, "Set up replicators for URI:" + uri.toString());
    }

    /**
     * Returns the URI for the remote database, based on the app's
     * configuration.
     *
     * URI is created based on user Cloudant credentials in cloudant_credentials.xml.
     *
     * @return the remote database's URI.
     * @throws URISyntaxException if the settings give an invalid URI.
     */
    private URI createServerURI() throws URISyntaxException {
        // We store this in plain text for the purposes of simple demonstration,
        // you might want to use something more secure. It is worth encrypting this information.
        String username = mContext.getResources().getString(R.string.cloudant_username);
        String dbName = mContext.getResources().getString(R.string.cloudant_dbname);
        String apiKey = mContext.getResources().getString(R.string.cloudant_api_key);
        String apiSecret = mContext.getResources().getString(R.string.cloudant_api_password);
        String host = username + ".cloudant.com";

        // We recommend always using HTTPS to talk to Cloudant.
        return new URI("https", apiKey + ":" + apiSecret, host, 443, "/" + dbName, null, null);
    }

    /**
     * Calls the MainActivity's replicationComplete method on the main thread,
     * as the complete() callback will probably come from a replicator worker
     * thread.
     */
    @Subscribe
    public void complete(ReplicationCompleted rc) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mListener != null) {
                    mListener.replicationComplete();
                }
            }
        });
    }

    /**
     * Calls the MainActivity's replicationComplete method on the main thread,
     * as the error() callback will probably come from a replicator worker
     * thread.
     */
    @Subscribe
    public void error(ReplicationErrored re) {
        Log.e(LOG_TAG, "Replication error:", re.errorInfo.getException());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mListener != null) {
                    mListener.replicationError();
                }
            }
        });
    }
}
