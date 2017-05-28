package com.ecommerce.ecommerpizzas.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class MenuListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_menu_item)
    TextView txtName;
    @BindView(R.id.menu_layout)
    RelativeLayout menuLayout;
    View itemView;
    @BindView(R.id.image_menu_item)
    ImageView imageView;
    protected Subscription mImageLoadSubscription;

    public MenuListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public RelativeLayout getMenuLayout() {
        return menuLayout;
    }

    public void bind(String menu){
        txtName.setText(menu);
    }

    public ImageView getImageView() {
        return imageView;
    }



}
