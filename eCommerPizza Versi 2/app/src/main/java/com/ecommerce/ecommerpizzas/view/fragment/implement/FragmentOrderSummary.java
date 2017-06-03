package com.ecommerce.ecommerpizzas.view.fragment.implement;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Asus on 03/06/2017.
 */

public class FragmentOrderSummary extends BaseFragment {
    @Override
    public void initView() {
        view = inflater.inflate(R.layout.order_summary, container, false);
        ButterKnife.bind(this, view);

    }
}
