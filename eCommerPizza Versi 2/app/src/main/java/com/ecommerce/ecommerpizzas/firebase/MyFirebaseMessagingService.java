package com.ecommerce.ecommerpizzas.firebase;

/**
 * Created by Nurochim on 27/09/2016.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Map<String, String> mapsData = remoteMessage.getData();
        intent.putExtra("status_order", mapsData.get("status_order"));
        intent.putExtra("from_foreground", true);
//        for(String key : mapsData.keySet()){
//            String statusOrder = mapsData.get(key);
//            Log.i("Status Order", statusOrder);
//        }






        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String notifTitle = notification.getTitle();

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        //Calling method to generate notification
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pizza_icon);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.pizza_icon_small)
                .setLargeIcon(bm)
                .setContentTitle(notifTitle)
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void zzm(Intent intent) {
        Intent launchIntent = new Intent(this, MainActivity.class);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.getString("status_order") != null && !bundle.getString("status_order").isEmpty()) {
                launchIntent.putExtra("status_order", bundle.getString("status_order"));
            }
        }

        launchIntent.setAction(Intent.ACTION_MAIN);
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* R    equest code */, launchIntent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.pizza_icon);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.pizza_icon_small)
                .setLargeIcon(rawBitmap)
                .setContentTitle(intent.getStringExtra("gcm.notification.title"))
                .setContentText(intent.getStringExtra("gcm.notification.body"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager)     getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }
}
