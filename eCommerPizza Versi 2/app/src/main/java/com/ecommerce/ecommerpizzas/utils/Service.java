package com.ecommerce.ecommerpizzas.utils;


import android.util.Log;

import com.ecommerce.ecommerpizzas.models.menu.MenuRespon;
import com.ecommerce.ecommerpizzas.models.post.PostEmployee;

import retrofit2.*;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetMenuListCallback callback) {

        return networkService.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MenuRespon>>() {
                    @Override
                    public Observable<? extends MenuRespon> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MenuRespon>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(MenuRespon menuRespon) {
                        callback.onSuccess(menuRespon);

                    }
                });
    }

    public interface GetMenuListCallback{
        void onSuccess(MenuRespon menuRespon);

        void onError(NetworkError networkError);
    }

    public Call<String> postUser(String name, String email, String contact_number, String position){
        final boolean[] status = {false};
        return networkService.savePost(name, email, contact_number, position);
    }

    public Call<String> postHeader(String idOrder,
                                   String name,
                                   String email,
                                   String contactNumber,
                                   String address,
                                   String statusOrder,
                                   String statusOrderDesc,
                                   String delveryFee,
                                   String subTotal,
                                   String total,
                                   String token, String paymentMethod){
        final boolean[] status = {false};
        return networkService.postHeader(Integer.valueOf(idOrder),
                name,
                email,
                contactNumber,
                address,
                statusOrder,
                statusOrderDesc,
                delveryFee,
                subTotal,
                total,
                token, paymentMethod);
    }

    public Call<String> postDetailOrder(String idOrder,
                                        String menuName,
                                        String detail,
                                        String harga,
                                        String size,
                                        String imageH,
                                        String imageD,
                                        String qty,
                                        String subTotal){
        final boolean[] status = {false};
        return networkService.postDetailOrder(Integer.valueOf(idOrder),
                menuName,
                detail,
                harga,
                size,
                imageH,
                imageD,
                qty,
                subTotal);
    }

    public interface GetPsotUserCallback{
        void onSuccess(String respon);

        void onError(NetworkError networkError);
    }
}
