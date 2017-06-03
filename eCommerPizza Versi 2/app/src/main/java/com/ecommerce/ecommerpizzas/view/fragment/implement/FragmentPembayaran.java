package com.ecommerce.ecommerpizzas.view.fragment.implement;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentPembayaran extends BaseFragment {
    @Override
    public void initView() {
        view = inflater.inflate(R.layout.pembayaran, container, false);
        ButterKnife.bind(this, view);

    }
}
