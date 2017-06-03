package com.ecommerce.ecommerpizzas.view.fragment.implement;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentMyCart extends BaseFragment {

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.my_cart, container, false);
        ButterKnife.bind(this, view);

    }
}
