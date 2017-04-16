package com.ecommerce.ecommercepizza.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecommerce.ecommercepizza.MainActivity;
import com.ecommerce.ecommercepizza.R;
import com.ecommerce.ecommercepizza.fragment.FragmentMenuDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nurochim on 08/10/2016.
 */

public class RecycleViewListAdapterMenu extends RecyclerView.Adapter<RecycleViewListAdapterMenu.MyViewHolder>{

    Context context;
    LayoutInflater inflater;
    List<String> listData = new ArrayList<>();
    public static String selectedMenu = "";
    public FragmentManager fragmentManager;

    public RecycleViewListAdapterMenu(Context context, List<String> listData, FragmentManager fragmentManager) {
        this.context = context;
        this.listData = listData;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.recycle_view_menu, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.MenuItem.setText(listData.get(position));
        holder.idMenu = String.valueOf(position);
        switch (position){
            case 0 : holder.image.setImageResource(R.drawable.beef_rendang);
                break;
            case 1 : holder.image.setImageResource(R.drawable.ayam_bakar);
                break;
            case 2 : holder.image.setImageResource(R.drawable.extravaganza);
                break;
            case 3 : holder.image.setImageResource(R.drawable.spicy_garlic_prawn);
                break;
            case 4 : holder.image.setImageResource(R.drawable.tandori_chicken);
                break;
            case 5 : holder.image.setImageResource(R.drawable.fire_breather);
                break;
            case 6 : holder.image.setImageResource(R.drawable.veggie_mania);
                break;
            case 7 : holder.image.setImageResource(R.drawable.veggie_supreme);
                break;
            case 8 : holder.image.setImageResource(R.drawable.sambal_beef);
                break;
            case 9 : holder.image.setImageResource(R.drawable.beef_papperoni_feast);
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView MenuItem;
        ImageView image;
        String idMenu = "";

        public MyViewHolder(View itemView){
            super(itemView);
            MenuItem = (TextView) itemView.findViewById(R.id.text_menu_item);
            image = (ImageView) itemView.findViewById(R.id.image_menu_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentMenuDetail fragment = new FragmentMenuDetail();
                    fragment.setIdMenu(idMenu);
                    FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();
                    fragmentTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                    fragmentTrans.replace(R.id.content_main, fragment);
                    fragmentTrans.addToBackStack(null).commit();
                    MainActivity.textTitle.setText(MenuItem.getText().toString());
                }
            });
        }

    }

}
