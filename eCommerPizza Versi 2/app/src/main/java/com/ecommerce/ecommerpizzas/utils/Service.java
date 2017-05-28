package com.ecommerce.ecommerpizzas.utils;


import com.ecommerce.ecommerpizzas.models.menu.MenuRespon;

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
}
