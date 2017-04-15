package com.ecommerce.ecommercepizza.fragment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ecommerce.ecommercepizza.R;
import com.ecommerce.ecommercepizza.common.BaseFragment;

/**
 * Created by Asus on 25/03/2017.
 */

public class AddUser extends BaseFragment{
    private static Context contexts;
    private static Activity activity;
    private Button saveBtn;
    EditText textUserName;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.add_user, container, false);
        contexts = getContext();
        fragmentManager= getFragmentManager();
        activity = getActivity();
        textUserName = (EditText) view.findViewById(R.id.input_user_name);
        saveBtn = (Button) view.findViewById(R.id.save_user);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textUserName.getText().toString().equals("add")){
                    String message = dbh.AddDataMenuPizza();
                    createToast(message);
                }
            }
        });
    }

}
