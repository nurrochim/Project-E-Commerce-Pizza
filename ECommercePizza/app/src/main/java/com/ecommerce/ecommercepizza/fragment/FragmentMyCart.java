package com.ecommerce.ecommercepizza.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ecommerce.ecommercepizza.R;
import com.ecommerce.ecommercepizza.adapter.RecycleViewListAdapterMyCart;
import com.ecommerce.ecommercepizza.common.BaseFragment;
import com.ecommerce.ecommercepizza.model.MyCart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 25/03/2017.
 */

public class FragmentMyCart extends BaseFragment{
    private static Context contexts;
    private static Activity activity;
    private Button checkOutBtn;
    EditText textUserName;
    List<MyCart> listData = new ArrayList<>();
    private RecyclerView listview;
    private RecycleViewListAdapterMyCart adapterListView;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.my_cart, container, false);
        contexts = getContext();
        fragmentManager= getFragmentManager();
        activity = getActivity();
        listview = (RecyclerView) view.findViewById(R.id.list_view_mycart);
        checkOutBtn = (Button) view.findViewById(R.id.btn_checkout);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textUserName.getText().toString().equals("add")){

                }
            }
        });

        for (int i = 1; i < 11; i++) {
            MyCart cart = new MyCart();
            cart.setMenuName("Beef Paperoni"+i);
            cart.setHarga("100,000,00");
            cart.setQty(String.valueOf(i));
            cart.setSize("10 Inchi");
            cart.setFidMenuPizza(String.valueOf(i));

            listData.add(cart);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterListView = new RecycleViewListAdapterMyCart(getContext(), listData, fragmentManager, getActivity());
        listview.setAdapter(adapterListView);
    }
}
