package com.ecommerce.ecommercepizza.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ecommerce.ecommercepizza.MainActivity;
import com.ecommerce.ecommercepizza.R;
import com.ecommerce.ecommercepizza.adapter.RecycleViewListAdapterMenu;
import com.ecommerce.ecommercepizza.common.BaseFragment;
import com.ecommerce.ecommercepizza.common.GridSpacingItemDecoration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 10/04/2017.
 */

public class FragmentMenu extends BaseFragment{
    RecyclerView listview;
    RecycleViewListAdapterMenu adapterView;
    List<String> data = new ArrayList<>();

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.menu_pizza, container, false);
        listview = (RecyclerView) view.findViewById(R.id.view_menu);
        MainActivity.textTitle.setText("Menu");
        if(data.isEmpty()) {
            //loadInit();
            data.add("Beef Rendang");
            data.add("Ayam Bakar");
            data.add("Extravaganza");
            data.add("Spicy Garlic Prawn");
            data.add("Tandori Chicken");
            data.add("Fire Breather");
            data.add("Veggie Mania");
            data.add("Veggie Supreme");
            data.add("Sambal Beef");
            data.add("Beef Papperoni Feast");

        }
    }

    private void loadInit() {
//        try {
//            data = serviceDao.queryForAll();
//            dbh.close();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listview.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //set up equal space between grid columns
        int spanCount = 2; // 3 columns
        int spacing = 20; // 20px
        boolean includeEdge = true;
        listview.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        listview.setAdapter(new RecycleViewListAdapterMenu(getActivity(),data, fragmentManager));
    }
}
