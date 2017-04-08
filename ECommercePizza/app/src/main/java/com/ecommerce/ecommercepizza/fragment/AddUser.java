package com.ecommerce.ecommercepizza.fragment;

import android.app.Activity;
import android.content.Context;

import com.ecommerce.ecommercepizza.R;
import com.ecommerce.ecommercepizza.common.BaseFragment;

/**
 * Created by Asus on 25/03/2017.
 */

public class AddUser extends BaseFragment{
    private static Context contexts;
    private static Activity activity;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.add_user, container, false);
        contexts = getContext();
        fragmentManager= getFragmentManager();
        activity = getActivity();
    }

}
