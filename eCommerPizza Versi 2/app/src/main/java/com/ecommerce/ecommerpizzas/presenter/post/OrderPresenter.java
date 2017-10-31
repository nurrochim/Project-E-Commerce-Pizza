package com.ecommerce.ecommerpizzas.presenter.post;

import android.util.Log;

import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.utils.Service;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentAddUser;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentPembayaran;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus on 10/06/2017.
 */

public class OrderPresenter {
    private final Service service;
    private final FragmentPembayaran view;

    public OrderPresenter(FragmentPembayaran view, Service service) {
        this.service = service;
        this.view = view;
    }

    public void postHeader(String idOrder,
                           String name,
                           String email,
                           String contactNumber,
                           String address,
                           String statusOrder,
                           String statusOrderDesc,
                           String delveryFee,
                           String subTotal,
                           String total,
                           String token,String paymentMethod){
        view.showWait();
        service.postHeader(idOrder,
                        name,
                        email,
                        contactNumber,
                        address,
                        statusOrder,
                        statusOrderDesc,
                        delveryFee,
                        subTotal,
                        total,
                        token, paymentMethod).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Post", "Post Succes "+response);
                Log.e("Post", "response code "+response.code());
                Log.e("Post", "response message "+response.message());
                Log.e("Post", "response body "+response.body());
                Log.e("Post", "response errorBody "+response.errorBody());
                if(response.code()==200){
                    //view.dialog.dismiss();
                    view.createToast("Succes Order");
                }else{
                    view.onFailure("");

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Post", "Post Error "+t.getMessage());
                view.onFailure("");
                view.createToast("Failed Save Order Header");
            }
        });
    }

    public void postDetail(List<MyCart> myCarts, String idOrder){
        for(MyCart cart : myCarts){
            Integer subTotal = Integer.valueOf(cart.getQty()) * Integer.valueOf(cart.getHarga());
            service.postDetailOrder(idOrder,
                    cart.getMenuName(),
                    cart.getDetail(),
                    cart.getHarga(),
                    cart.getSize(),
                    "-",
                    "-",
                    cart.getQty(),
                    subTotal.toString()
                    ).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.e("Post", "Post Succes "+response);
                    Log.e("Post", "response code "+response.code());
                    Log.e("Post", "response cmessage "+response.message());
                    Log.e("Post", "response body "+response.body());
                    //Log.e("Post", "response errorBody "+response.errorBody().toString());
                    if(response.code()==200){
                        view.dialog.dismiss();
//                        view.createToast("Succes Save Order Detail");
                    }else{
                        view.onFailure("");
//                        BufferedReader reader = null;
//                        StringBuilder sb = new StringBuilder();
//                        try {
//                            reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
//                            String line;
//                            try {
//                                while ((line = reader.readLine()) != null) {
//                                    sb.append(line);
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        String finallyError = sb.toString();
//                        Log.e("Post", "response errorBody "+finallyError);
//                        view.createToast("Failed Save Order Detail");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("Post", "Post Error "+t.getMessage());
                    view.onFailure("");
                }
            });
           // break;
//            view.createToast("Finish All Post");
//            view.dialog.dismiss();
        }



    }
}
