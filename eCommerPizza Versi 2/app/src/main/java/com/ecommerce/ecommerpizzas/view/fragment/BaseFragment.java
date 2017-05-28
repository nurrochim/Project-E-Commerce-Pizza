package com.ecommerce.ecommerpizzas.view.fragment;

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

import com.ecommerce.ecommerpizzas.deps.DaggerDeps;
import com.ecommerce.ecommerpizzas.deps.Deps;
import com.ecommerce.ecommerpizzas.utils.NetworkModule;

import java.io.File;


/**
 * Created by Asus on 21/05/2017.
 */

public class BaseFragment extends Fragment {
    protected FragmentManager fragmentManager;
    public LayoutInflater inflater;
    protected View view;
    protected ViewGroup container;
    Deps deps;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        fragmentManager = getActivity().getSupportFragmentManager();

        File cacheFile = new File(getActivity().getBaseContext().getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

        initView();
        return view;
    }

    public void initView(){

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

    public Deps getDeps() {
        return deps;
    }
}
