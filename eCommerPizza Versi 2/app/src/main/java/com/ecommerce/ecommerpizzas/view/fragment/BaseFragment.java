package com.ecommerce.ecommerpizzas.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.utils.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


/**
 * Created by Asus on 21/05/2017.
 */

public class BaseFragment extends Fragment {
    protected FragmentManager fragmentManager;
    public LayoutInflater inflater;
    protected View view;
    protected ViewGroup container;
    public SharedPreferences.Editor editorSharedPreference ;
    public SharedPreferences sharedPreference ;
    public DatabaseHelper dbh ;
    public SQLiteDatabase db;
    public Dao<MyCart, String> myCartsDao = null;

    public FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        fragmentManager = getActivity().getSupportFragmentManager();

        //sharedpreference
        editorSharedPreference = getContext().getSharedPreferences("ReUse_Variable", Context.MODE_PRIVATE).edit();
        sharedPreference = getContext().getSharedPreferences("ReUse_Variable", Context.MODE_PRIVATE);

        initView();
        return view;
    }

    public void initView(){

    }

    public void openDatabaseHelper(){
        dbh = new DatabaseHelper(getActivity());
        try {
            myCartsDao = dbh.getMyCartDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createToast(String text){
        CharSequence textToast = text;
        Toast toast = Toast.makeText(getContext(),textToast, Toast.LENGTH_SHORT);
        TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
        if( textView != null) textView.setGravity(Gravity.CENTER);
        toast.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void openFragment(Fragment fragment, String Title, Boolean isAnimationBack){
        fragmentTransaction = fragmentManager.beginTransaction();
        if(isAnimationBack){
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        }else{
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.hide(this);
        fragmentTransaction.addToBackStack(this.getClass().getName()).commit();
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
