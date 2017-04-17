package com.ecommerce.ecommercepizza.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecommerce.ecommercepizza.R;
import com.ecommerce.ecommercepizza.model.MyCart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nurochim on 08/10/2016.
 */

public class RecycleViewListAdapterMyCart extends RecyclerView.Adapter<RecycleViewListAdapterMyCart.MyViewHolderMyCartItem>{

    Context context;
    LayoutInflater inflater;
    List<MyCart> listData = new ArrayList<>();
    FragmentManager fragmentManager;
    Activity activity;

    public RecycleViewListAdapterMyCart(Context context, List<MyCart> listData, FragmentManager fragmentManager, Activity activity) {
        this.context = context;
        this.listData = listData;
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }
    @Override
    public MyViewHolderMyCartItem onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.recycle_view_mycart, parent, false);
        return new MyViewHolderMyCartItem(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolderMyCartItem holder, int position) {
        MyCart cart = listData.get(position);
        holder.cart = cart;
        holder.textPizzaName.setText(cart.getMenuName());
        holder.textSize.setText(cart.getSize());
        holder.textQty.setText(cart.getQty());
        holder.textPrice.setText(cart.getHarga());

        switch (position) {
            case 0:
                holder.image.setImageResource(R.drawable.beef_rendang);
                break;
            case 1:
                holder.image.setImageResource(R.drawable.ayam_bakar);
                break;
            case 2:
                holder.image.setImageResource(R.drawable.extravaganza);
                break;
            case 3:
                holder.image.setImageResource(R.drawable.spicy_garlic_prawn);
                break;
            case 4:
                holder.image.setImageResource(R.drawable.tandori_chicken);
                break;
            case 5:
                holder.image.setImageResource(R.drawable.fire_breather);
                break;
            case 6:
                holder.image.setImageResource(R.drawable.veggie_mania);
                break;
            case 7:
                holder.image.setImageResource(R.drawable.veggie_supreme);
                break;
            case 8:
                holder.image.setImageResource(R.drawable.sambal_beef);
                break;
            case 9:
                holder.image.setImageResource(R.drawable.beef_papperoni_feast);
                break;
        }
        }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolderMyCartItem extends RecyclerView.ViewHolder {
        TextView textPizzaName, textQty, textPrice, textSize;
        MyCart cart;
        ImageView image;
        //PopupInput popupInput;

        public MyViewHolderMyCartItem(View itemView) {
            super(itemView);
            textPizzaName = (TextView) itemView.findViewById(R.id.text_pizza_name);
            textSize = (TextView) itemView.findViewById(R.id.text_size_cart);
            textQty = (TextView) itemView.findViewById(R.id.text_qty_cart);
            textPrice = (TextView) itemView.findViewById(R.id.text_price);
            image = (ImageView) itemView.findViewById(R.id.image_cart_menu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

}
