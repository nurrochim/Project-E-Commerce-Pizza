package com.ecommerce.ecommercepizza.fragment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecommerce.ecommercepizza.R;
import com.ecommerce.ecommercepizza.common.BaseFragment;
import com.ecommerce.ecommercepizza.model.MenuPizza;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Asus on 25/03/2017.
 */

public class FragmentMenuDetail extends BaseFragment{
    private static Context contexts;
    private static Activity activity;
    private Button addToCart;
    TextView textMenu,textHarga, textDetail;
    RelativeLayout imageMenuDetail;
    private String idMenu;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.fragment_menu_detail, container, false);
        contexts = getContext();
        fragmentManager= getFragmentManager();
        activity = getActivity();
        textMenu = (TextView) view.findViewById(R.id.text_menu_name);
        textHarga = (TextView) view.findViewById(R.id.text_harga);
        textDetail = (TextView) view.findViewById(R.id.text_detail);
        addToCart = (Button) view.findViewById(R.id.add_to_cart);
        imageMenuDetail = (RelativeLayout)  view.findViewById(R.id.layout_menu_detail);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loadData();
    }

    public void loadData(){
            // load list view
            try {
                Integer idMenuInt = Integer.valueOf(idMenu)+1;
                List<MenuPizza> listMenu = menuPizzaDao.queryForEq(MenuPizza.clm_id_menu, String.valueOf(idMenuInt));
                dbh.close();
                for(MenuPizza menuPizza : listMenu){
                    textMenu.setText(menuPizza.getMenuName());
                    textHarga.setText(menuPizza.getHarga()+" ("+menuPizza.getSize()+")");
                    textDetail.setText(menuPizza.getDetail());

                    switch (idMenuInt) {
                        case 1:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.beef_rendang1));
                            break;
                        case 2:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.ayam_bakar1));
                            break;
                        case 3:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.extravaganza1));
                            break;
                        case 4:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.spicy_garlic1));
                            break;
                        case 5:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.tundori_chicken1));
                            break;
                        case 6:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.fire_breather1));
                            break;
                        case 7:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.vegie_mania1));
                            break;
                        case 8:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.vegie_supreme1));
                            break;
                        case 9:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.sambal_beef1));
                            break;
                        case 10:
                            imageMenuDetail.setBackground(getResources().getDrawable(R.drawable.beef_paperoni1));
                            break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }
}
