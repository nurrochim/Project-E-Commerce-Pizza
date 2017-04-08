package com.ecommerce.ecommercepizza.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.ecommercepizza.MainActivity;
import com.ecommerce.ecommercepizza.R;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

/**
 * Created by Nurochim on 07/10/2016.
 */

public class BaseFragment extends Fragment {

    public LayoutInflater inflater;
    public View view;
    public ViewGroup container;
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;

    public DatabaseHelper dbh ;
    public SQLiteDatabase db;
    public Cursor cursor;
    public SharedPreferences.Editor editorSharedPreference ;
    public SharedPreferences sharedPreference ;
    public CharSequence textToast = "";
 //   public Dao<User, String> userDao = null;
    public static String keyIpAddress = "IpAddress";
    public static String keyAddres = "Address";
    public String ipServer = null;
    public String idUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbh = new DatabaseHelper(getActivity());
        db = dbh.getWritableDatabase();
        //SugarContext.init(getContext());

        this.inflater = inflater;
        this.container = container;

        fragmentManager = getActivity().getSupportFragmentManager();


        editorSharedPreference = getContext().getSharedPreferences("ReUse_Variable", Context.MODE_PRIVATE).edit();
        sharedPreference = getContext().getSharedPreferences("ReUse_Variable", Context.MODE_PRIVATE);
        ipServer = sharedPreference.getString("IpAddress","");
        idUser = sharedPreference.getString("IdUser","");
        // load Service DB
//        try {
//            //userDao = dbh.getUserDao();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        initView();

        return view;
    }

    public void initView(){

    }

    public void openFragment(Fragment fragment, String Title, Boolean isAnimationBack){
        fragmentTransaction = fragmentManager.beginTransaction();
        if(isAnimationBack){
           fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        }else{
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.addToBackStack(null).commit();
        if(Title!=null && !Title.isEmpty()){
            MainActivity.textTitle.setText(Title);
        }
    }


    public void createToast(String text){
        textToast = text;
        Toast toast = Toast.makeText(getContext(),textToast, Toast.LENGTH_SHORT);
        TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
        if( textView != null) textView.setGravity(Gravity.CENTER);
        toast.show();
    }

    public void openDatabaseHelper(){
        dbh = new DatabaseHelper(getActivity());
//        try {
//            //userDao = dbh.getUserDao();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    public void saveSharedPreference(String key, Object value){
        if(value instanceof String){
            editorSharedPreference.putString(key, value.toString());
        }

        editorSharedPreference.commit();
    }

    public Object getValueSharedPreference(String key){
        Object object = new Object();
        object = sharedPreference.getString(key,"");
        return object;
    }
}
