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

import com.ecommerce.ecommerpizzas.R;


/**
 * Created by Asus on 21/05/2017.
 */

public class BaseFragment extends Fragment {
    protected FragmentManager fragmentManager;
    public LayoutInflater inflater;
    protected View view;
    protected ViewGroup container;

    public FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        fragmentManager = getActivity().getSupportFragmentManager();

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

    public void openFragment(Fragment fragment, String Title, Boolean isAnimationBack){
        fragmentTransaction = fragmentManager.beginTransaction();
        if(isAnimationBack){
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        }else{
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
}
