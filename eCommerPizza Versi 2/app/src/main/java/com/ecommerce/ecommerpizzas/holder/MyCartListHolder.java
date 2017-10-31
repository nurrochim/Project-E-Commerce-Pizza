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
import com.ecommerce.ecommerpizzas.utils.URLs;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

public class MyCartListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.layout_menu_myCart)
    RelativeLayout layoutMyCart;
    View itemView;
    @BindView(R.id.image_cart_menu)
    ImageView imageView;
    @BindView(R.id.text_pizza_name)
    TextView txtName;
    @BindView(R.id.text_size_cart)
    TextView txtSize;
    @BindView(R.id.text_qty_cart)
    TextView txtQty;
    @BindView(R.id.text_price)
    TextView txtPrice;
    @BindView(R.id.layout_image_remove)
    LinearLayout removeCart;
    @BindDrawable(R.drawable.loading)
    Drawable loading;
    @BindDrawable(R.drawable.error)
    Drawable error;

    public MyCartListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }


    public void bind(MyCart myCart, Context context){
        txtName.setText(myCart.getMenuName());
        txtSize.setText(myCart.getSize());
        txtQty.setText(myCart.getQty());
        Double price = Double.parseDouble(myCart.getHarga());
        txtPrice.setText(String.format("%,.2f",price));
        Glide.with(context)
                .load(URLs.getStorageUrl()+myCart.getImage())
                .placeholder(loading)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .animate(R.anim.fade_in)
                .fitCenter()
                //.skipMemoryCache(true)
                .into(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public LinearLayout getRemoveCart() {
        return removeCart;
    }

    public RelativeLayout getLayoutMyCart() {
        return layoutMyCart;
    }
}
