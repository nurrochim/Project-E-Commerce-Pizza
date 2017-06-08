package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.adapter.ListAdapter;
import com.ecommerce.ecommerpizzas.holder.MyCartListHolder;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentMyCart extends BaseFragment {
    private ListAdapter adapter;
    @BindView(R.id.list_view_mycart)
    RecyclerView listview;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.my_cart, container, false);
        ButterKnife.bind(this, view);

        loadData();
    }

    public void loadData(){
        try {
            openDatabaseHelper();
            List<MyCart> myCartList = myCartsDao.queryForAll();
            adapter = new ListAdapter<MyCart,MyCartListHolder>(
                    R.layout.recycle_view_mycart,
                    MyCartListHolder.class,
                    MyCart.class,
                    myCartList
                        ) {
                @Override
                protected void bindView(MyCartListHolder holder, MyCart model, int position) {
                holder.bind(model, getContext());
                }
            };
            listview.setLayoutManager(new LinearLayoutManager(getContext()));
            listview.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbh.close();
        }
    }
}
