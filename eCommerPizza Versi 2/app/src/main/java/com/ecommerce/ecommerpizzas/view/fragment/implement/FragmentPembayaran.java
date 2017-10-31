package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.deps.DaggerDeps;
import com.ecommerce.ecommerpizzas.deps.Deps;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.presenter.post.OrderPresenter;
import com.ecommerce.ecommerpizzas.utils.NetworkModule;
import com.ecommerce.ecommerpizzas.utils.Service;
import com.ecommerce.ecommerpizzas.view.fragment.AddUserFragment;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;
import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentPembayaran extends BaseFragment implements AddUserFragment {
    @BindView(R.id.text_name)
    TextView userName;
    @BindView(R.id.textContact)
    TextView contactNo;
    @BindView(R.id.textAddress)
    TextView alamat;
    @BindView(R.id.text_email)
    TextView emailTextView;
    @BindView(R.id.text_order_date)
    TextView oderDate;
    @BindView(R.id.btn_cash)
    ImageButton btnCash;
    @BindView(R.id.btn_credit)
    ImageButton btnCredit;
    @BindView(R.id.btn_debet)
    ImageButton btnDebet;
    @BindView(R.id.layout_cash)
    LinearLayout layoutCash;
    @BindView(R.id.layout_credit)
    LinearLayout layoutCredit;
    @BindView(R.id.layout_debet)
    LinearLayout layoutDebet;
    @BindDrawable(R.drawable.shape_radius_layout_red)
    Drawable backgrounLayoutRed;
    @BindDrawable(R.drawable.shape_radius_layout)
    Drawable backgrounLayout;

    String dateNow, idOrder, name,email,contactNumber,address;
    String paymentMethod = "Cash";


    @Inject
    public Service service;
    Deps deps;
    public ProgressDialog dialog;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.pembayaran, container, false);
        ButterKnife.bind(this, view);

        File cacheFile = new File(getActivity().getBaseContext().getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
        deps.inject(this);
        changeBackgrounLayout(true, false, false);
        loadData();
    }

    private void loadData() {
        Date dtnow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm");
        dateNow = sdf.format(dtnow);

        // prepare variable
        idOrder = System.currentTimeMillis()+"";
        idOrder = idOrder.substring(idOrder.length()-8, idOrder.length());
        name = getValueSharedPreference("userName").toString();
        email = getValueSharedPreference("email").toString();
        contactNumber = getValueSharedPreference("contactNo").toString();
        address = getValueSharedPreference("alamat").toString();

        userName.setText(name);
        contactNo.setText(contactNumber);
        alamat.setText(address);
        emailTextView.setText(email);
        oderDate.setText("Order Date : "+dateNow);
    }



    @Override
    public void showWait() {
        dialog = ProgressDialog.show(getActivity(), "","Loading. Please wait...", true);
    }

    @Override
    public void removeWait() {
        dialog.dismiss();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        dialog.dismiss();
        createToast("Sory... Something Wrong... \n Try again later");
    }

    @OnClick(R.id.submit_order)
    public void submitOrder(){
        Date dtnow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm");
        String dateNow = sdf.format(dtnow);

        String subTotal = "";
        String total = "";
        List<MyCart> myCartList = new ArrayList<>();
        List<MyCart> myCartList1 = new ArrayList<>();

        // get My Cart
        try {
            openDatabaseHelper();
            myCartList = myCartsDao.queryForAll();
            double sumPrice = 0;
            for (MyCart myCart : myCartList) {
                sumPrice += Double.parseDouble(myCart.getHarga());
                if(myCartList1.size()==0){
                    myCartList1.add(myCart);
                }
            }
            subTotal = String.format("%,.2f", sumPrice);
            total = String.format("%,.2f", (sumPrice + 15000));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbh.close();
        }

            // prepare variable
        String statusOrder = "1";
        String statusOrderDesc = "Waiting Respon";
        String delveryFee = dateNow;

        String token = FirebaseInstanceId.getInstance().getToken();

        OrderPresenter orderPresenter = new OrderPresenter(this, service);

//         submit header
        orderPresenter.postHeader(
                idOrder,
                name.isEmpty()?"-":name,
                email.isEmpty()?"-":email,
                contactNumber.isEmpty()?"-":contactNumber,
                address.isEmpty()?"-":address,
                statusOrder,
                statusOrderDesc,
                delveryFee,
                subTotal,
                total,
                token,
                paymentMethod
        );

        // submit order detail
        orderPresenter.postDetail(myCartList,idOrder);

        // update status order
        saveSharedPreference("statusOrder", 1);
    }

    @OnClick(R.id.btn_cash)
    public void cashClick(){
        changeBackgrounLayout(true, false, false);
    }

    @OnClick(R.id.btn_credit)
    public void creditClick(){
        changeBackgrounLayout(false, false, true);
    }

    @OnClick(R.id.btn_debet)
    public void debetClick(){
        changeBackgrounLayout(false, true, false);
    }

    public void changeBackgrounLayout(boolean cash, boolean debet, boolean credit){
        if(cash) {
            layoutCash.setBackground(backgrounLayoutRed);
            layoutCredit.setBackground(backgrounLayout);
            layoutDebet.setBackground(backgrounLayout);
            paymentMethod = "Cash";
        }
        if(credit) {
            layoutCash.setBackground(backgrounLayout);
            layoutCredit.setBackground(backgrounLayoutRed);
            layoutDebet.setBackground(backgrounLayout);
            paymentMethod = "Credit Card";
        }
        if(debet) {
            layoutCash.setBackground(backgrounLayout);
            layoutCredit.setBackground(backgrounLayout);
            layoutDebet.setBackground(backgrounLayoutRed);
            paymentMethod = "Debet Card";
        }
    }
}
