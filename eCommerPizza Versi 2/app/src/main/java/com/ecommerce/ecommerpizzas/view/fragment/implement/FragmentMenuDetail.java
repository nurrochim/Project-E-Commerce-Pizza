package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.models.menu.MenuListData;
import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;
import com.ecommerce.ecommerpizzas.utils.URLs;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Asus on 25/03/2017.
 */

public class FragmentMenuDetail extends BaseFragment{
    @BindView(R.id.add_to_cart)
    Button addToCart;
    @BindView(R.id.text_menu_name)
    TextView textMenu;
    @BindView(R.id.text_harga)
    TextView textHarga;
    @BindView(R.id.text_detail)
    TextView textDetail;
    @BindView(R.id.layout_menu_detail)
    RelativeLayout imageMenuDetail;
    @BindDrawable(R.drawable.loading)
    Drawable loading;

    @BindDrawable(R.drawable.error)
    Drawable error;
    MenuListData model;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.fragment_menu_detail, container, false);
        ButterKnife.bind(this, view);
        showDetail();

    }

    public void showDetail(){
        textMenu.setText(model.getMenuName());
        // format harga
        Double number = Double.valueOf(model.getMenuHarga());

        DecimalFormat dec = new DecimalFormat("#,###.##");
        dec.setMinimumFractionDigits(2);
        String harga = dec.format(number);

        textHarga.setText("Rp. "+harga);
        textDetail.setText("Size "+model.getMenuSize()+"\n"+model.getMenuDetail());

        Glide.with(getContext())
                .load(URLs.getStorageUrl()+model.getImage2())
                .asBitmap()
                .placeholder(loading)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .animate(R.anim.fade_in)
                .into(new SimpleTarget<Bitmap>(100,100) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(resource);
                        imageMenuDetail.setBackground(bitmapDrawable);
                    }
                });;
    }

    public MenuListData getModel() {
        return model;
    }

    public void setModel(MenuListData model) {
        this.model = model;
    }

    @OnClick(R.id.add_to_cart)
    public void addToCart(){
        try {
            openDatabaseHelper();
            List<MyCart> myCarts = myCartsDao.queryForEq(MyCart.clm_fidMenu, model.getId());
            for(MyCart cart : myCarts){
                if(cart.getFidMenuPizza()==model.getId()){
                    Integer qty = Integer.valueOf(cart.getQty())+1;
                    cart.setQty(qty.toString());
                    Integer harga = Integer.valueOf(model.getMenuHarga());
                    cart.setHarga(String.valueOf(harga*qty));
                    myCartsDao.update(cart);
                }
            }

            if(myCarts.isEmpty()){
                MyCart myCart = new MyCart(model.getId(), model.getMenuName(), model.getMenuDetail(), model.getMenuHarga(), model.getMenuSize(), "1", model.getImage1());
                myCartsDao.create(myCart);
            }

            // open fragment MyCart
            openFragment(new FragmentMyCart(), "", false);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbh.close();
        }
    }
}
