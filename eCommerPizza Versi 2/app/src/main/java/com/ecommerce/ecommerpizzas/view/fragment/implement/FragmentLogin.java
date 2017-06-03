package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.widget.Button;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentLogin extends BaseFragment {
    @BindView(R.id.btn_order_as_guest)
    Button btnOrderAsGuest;
    @BindView(R.id.btn_register)
    Button btnRegsister;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.login, container, false);
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.btn_order_as_guest, R.id.btn_register})
    public void openAddUser(){
        openFragment(new FragmentAddUser(), "", false);
    }
}
