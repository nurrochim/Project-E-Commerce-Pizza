package com.ecommerce.ecommerpizzas.view.popup;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMyCart;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 18/07/2017.
 */

public class PopupInput extends DialogFragment {

    @BindView(R.id.text_menu_name)
    TextView textMenuName;
    @BindView(R.id.text_input_number)
    TextView textInputNumber;
    FragmentMyCart myCartFragment;
    MyCart myCart;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.popup_input);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        ButterKnife.bind(this, dialog);

        textInputNumber.setText(myCart.getQty());

        return dialog;
    }

    public void setParam(FragmentMyCart myCartFragment, MyCart myCart) {
        this.myCartFragment = myCartFragment;
        this.myCart = myCart;
    }

    @OnClick(R.id.btnDone)
    public void btnOkListener(){
        try {

            if(!textInputNumber.getText().toString().equals(myCart.getQty())) {
                Integer harga = Integer.valueOf(myCart.getHarga())/Integer.valueOf(myCart.getQty());
                Integer qty = Integer.valueOf(textInputNumber.getText().toString());

                myCartFragment.openDatabaseHelper();
                myCart.setQty(textInputNumber.getText().toString());

                myCart.setHarga(String.valueOf(harga * qty));
                myCartFragment.myCartsDao.update(myCart);
                myCartFragment.createToast("Succes update quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            myCartFragment.createToast("Sory... Something wrong");
        }finally {
            myCartFragment.dbh.close();
        }

        myCartFragment.loadData();
        dismiss();
    }

    @OnClick(R.id.btnCancel)
    public void btnCancelListener(){
        dismiss();
    }

    @OnClick(R.id.button_add)
    public void btnAddListener(){
        int number = Integer.valueOf(textInputNumber.getText().toString());
        if(number<50){
            number++;
        }
        textInputNumber.setText(String.valueOf(number));
    }

    @OnClick(R.id.button_min)
    public void btnMinListener(){
        int number = Integer.valueOf(textInputNumber.getText().toString());
        if(number>1){
            number--;
        }
        textInputNumber.setText(String.valueOf(number));
    }
}
