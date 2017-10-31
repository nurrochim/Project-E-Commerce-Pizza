package com.ecommerce.ecommerpizzas.holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.models.statusOrder.StatusOrderData;
import com.ecommerce.ecommerpizzas.utils.URLs;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatusOrderListHolder extends RecyclerView.ViewHolder {

    View itemView;
    @BindView(R.id.image_checklist)
    ImageView imageView;
    @BindView(R.id.text_status)
    TextView txtStatus;
    @BindView(R.id.layout_status_order)
    RelativeLayout layoutStatusOrder;

    public StatusOrderListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public void bind(StatusOrderData statusOrderData, Context context){
        txtStatus.setText(statusOrderData.getStatus());
        //imageView.setImageResource(R.drawable.check_mark);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public RelativeLayout getLayoutStatusOrder() {
        return layoutStatusOrder;
    }
}
