package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.view.View;
import android.widget.Button;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentAddUser extends BaseFragment {
    @BindView(R.id.save_user)
    Button saveUser;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.add_user, container, false);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.save_user)
    public void saveUser(){
        openFragment(new FragmentMenuImp(), "", false);
    }

}
