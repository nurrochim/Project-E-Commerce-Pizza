package com.ecommerce.ecommerpizzas.presenter.menu;

import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;
import com.ecommerce.ecommerpizzas.models.menu.MenuRespon;
import com.ecommerce.ecommerpizzas.utils.NetworkError;
import com.ecommerce.ecommerpizzas.utils.Service;
import com.ecommerce.ecommerpizzas.view.fragment.MenuFragment;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMenuImp;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class MenuPresenter {
    private final Service service;
    private final MenuFragment view;
    private CompositeSubscription subscriptions;

    public MenuPresenter(MenuFragment view, Service service){
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }



    public void getMenuList() {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetMenuListCallback() {
            @Override
            public void onSuccess(MenuRespon menuRespon) {
                view.removeWait();
                view.getMenuListSuccess(menuRespon);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
