package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.adapter.ListAdapter;
import com.ecommerce.ecommerpizzas.holder.MyCartListHolder;
import com.ecommerce.ecommerpizzas.holder.StatusOrderListHolder;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.models.statusOrder.StatusOrderData;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;
import com.ecommerce.ecommerpizzas.view.fragment.fragmentTab.FragmentConfirmation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 10/06/2017.
 */

public class FragmentStatusOrder extends BaseFragment {
    private ListAdapter adapter;
    @BindView(R.id.list_view_status_order)
    RecyclerView listview;
    Integer statusOrder = 1;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.status_order, container, false);
        ButterKnife.bind(this, view);
        loadData();
    }

    public void loadData(){
        if(statusOrder>1){
            saveSharedPreference("statusOrder", statusOrder);
        }
        if(statusOrder==1) {
            Object status = getValueSharedPreferenceInt("statusOrder");
            if(status!=null && !status.toString().isEmpty()){
                statusOrder = (Integer) status;
            }
        }

        // write list
        List<StatusOrderData> statusOrderDataList = new ArrayList<>();
        for (int i = 0; i < statusOrder; i++) {
            StatusOrderData st = new StatusOrderData();

            if(i==0){
                st.setStatus("Waiting List");
            }else if(i==1){
                st.setStatus("Confirmed");
            }else if(i==2){
                st.setStatus("On Procces");
            }else if(i==3){
                st.setStatus("On Delivery");
            }else if(i==4){
                st.setStatus("Finish Payment");
            }

            statusOrderDataList.add(st);
        }

        // load adapter
        adapter = new ListAdapter<StatusOrderData,StatusOrderListHolder>(
                R.layout.recycle_view_status_order,
                StatusOrderListHolder.class,
                StatusOrderData.class,
                statusOrderDataList
        ) {
            @Override
            protected void bindView(StatusOrderListHolder holder, StatusOrderData model, int position) {
                holder.bind(model, getContext());
                holder.getLayoutStatusOrder().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(model.getStatus().equals("Finish Payment")){
                            openFragment(new FragmentFinishOrder(), "", false);
                        }
                    }
                });
            }
        };
        listview.setLayoutManager(new LinearLayoutManager(getContext()));
        listview.setAdapter(adapter);
    }

    public Integer getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(Integer statusOrder) {
        this.statusOrder = statusOrder;
    }
}
