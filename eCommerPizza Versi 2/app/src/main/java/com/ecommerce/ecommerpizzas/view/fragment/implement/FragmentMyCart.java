package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.adapter.ListAdapter;
import com.ecommerce.ecommerpizzas.holder.MyCartListHolder;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;
import com.ecommerce.ecommerpizzas.view.fragment.fragmentTab.FragmentConfirmation;
import com.ecommerce.ecommerpizzas.view.popup.PopupInput;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentMyCart extends BaseFragment {
    private ListAdapter adapter;
    @BindView(R.id.list_view_mycart)
    RecyclerView listview;
    @BindView(R.id.text_sum)
    TextView textSum;
    @BindView(R.id.text_fee)
    TextView textFee;
    @BindView(R.id.text_total)
    TextView textTotal;

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
                    holder.getLayoutMyCart().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PopupInput popupInput = new PopupInput();
                            popupInput.setParam(FragmentMyCart.this, model);
                            popupInput.show(fragmentManager, "Test");

                        }
                    });
                    holder.getRemoveCart().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                openDatabaseHelper();
                                myCartsDao.delete(model);
                                createToast("Remove Item Succes");
                                loadData();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                createToast("Sory... Something wrong");
                            }finally {
                                dbh.close();
                            }

                        }
                    });
                }
            };
            listview.setLayoutManager(new LinearLayoutManager(getContext()));
            listview.setAdapter(adapter);

            // calculate total
            if(myCartList.size()>0) {
                double sumPrice = 0;
                for (MyCart myCart : myCartList) {
                    sumPrice += Double.parseDouble(myCart.getHarga());
                }
                textSum.setText(String.format("%,.2f", sumPrice));
                textTotal.setText(String.format("%,.2f", (sumPrice + 15000)));
            }else{
                textSum.setText("");
                textFee.setText("");
                textTotal.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbh.close();
        }
    }

    @OnClick(R.id.btn_checkout)
    public void checkOut(){
        openFragment(new FragmentConfirmation(), "", false);
    }
}
