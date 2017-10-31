package com.ecommerce.ecommerpizzas.presenter.post;

import android.util.Log;

import com.ecommerce.ecommerpizzas.utils.Service;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentAddUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Asus on 10/06/2017.
 */

public class UserPresenter {
    private final Service service;
    private final FragmentAddUser view;

    public UserPresenter(FragmentAddUser view, Service service) {
        this.service = service;
        this.view = view;
    }

    public void postUser(String name, String email, String contact_number, String position){
        view.showWait();
        service.postUser(name, email, contact_number, position).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Post", "Post Succes "+response);
                Log.i("Post", "response code "+response.code());
                Log.i("Post", "response cmessage "+response.message());
                if(response.code()==200){
                    view.dialog.dismiss();
                    view.createToast("Succes Save User");
                    view.openMenu();
                }else{
                    view.onFailure("");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Post", "Post Error "+t.getMessage());
                view.onFailure("");
            }
        });
    }
}
