package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.deps.DaggerDeps;
import com.ecommerce.ecommerpizzas.deps.Deps;
import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.presenter.post.UserPresenter;
import com.ecommerce.ecommerpizzas.utils.NetworkModule;
import com.ecommerce.ecommerpizzas.utils.Service;
import com.ecommerce.ecommerpizzas.view.fragment.AddUserFragment;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 31/05/2017.
 */

public class FragmentAddUser extends BaseFragment implements AddUserFragment {
    @BindView(R.id.save_user)
    Button saveUser;

    @Inject
    public Service service;

    @BindView(R.id.input_user_name)
    EditText userName;
    @BindView(R.id.input_no_telpon)
    EditText contactNo;
    @BindView(R.id.input_alamat)
    EditText alamat;
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.input_pass)
    EditText password;
    Boolean isGuest = false;

    UserPresenter userPresenter;
    Deps deps;
    public ProgressDialog dialog;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.add_user, container, false);
        ButterKnife.bind(this, view);

        File cacheFile = new File(getActivity().getBaseContext().getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
        deps.inject(this);

//        email.setText("indrawan@gmail.com");
//        alamat.setText("Jakarta");
//        userName.setText("Indrawan");
//        contactNo.setText("9723123");

        if(isGuest){
            password.setVisibility(View.GONE);
        }

    }
//
//    @OnClick(R.id.save_user)
//    public void saveUser(){
//        openFragment(new FragmentMenuImp(), "", false);
//    }

    @Override
    public void showWait() {
        //progressBar.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.save_user)
    public void saveUserAction(){
        // delc
        String name = userName.getText().toString();
        if(name.equals("")){
            try {
                openDatabaseHelper();
                List<MyCart> list = myCartsDao.queryForAll();
                for (MyCart cart: list) {
                    myCartsDao.delete(cart);
                }
                saveSharedPreference("statusOrder", 0);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbh.close();
            }
        }else{
            userPresenter = new UserPresenter(this, service);
            userPresenter.postUser(userName.getText().toString(), email.getText().toString(), contactNo.getText().toString(), "Guest");

            // get token for Firebase notification
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.i("Token Firebase", token);
    }


    }

    public Boolean getGuest() {
        return isGuest;
    }

    public void setGuest(Boolean guest) {
        isGuest = guest;
    }

    public void openMenu(){
        openFragment(new FragmentMenuImp(), "", false);
        saveSharedPreference("userName", userName.getText().toString());
        saveSharedPreference("email", email.getText().toString());
        saveSharedPreference("contactNo", contactNo.getText().toString());
        saveSharedPreference("alamat", alamat.getText().toString());
        saveSharedPreference("statusOrder", 0);
    }
}
