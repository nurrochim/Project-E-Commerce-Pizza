package com.ecommerce.ecommerpizzas.deps;


import com.ecommerce.ecommerpizzas.utils.NetworkModule;
import com.ecommerce.ecommerpizzas.view.fragment.MenuFragment;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentAddUser;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMenuImp;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentPembayaran;

import dagger.Component;
import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(FragmentMenuImp menuFragment);
    void inject(FragmentAddUser addUser);
    void inject(FragmentPembayaran fragmentPembayaran);
}





