package com.ecommerce.ecommerpizzas.utils;


import com.ecommerce.ecommerpizzas.models.menu.MenuRespon;
import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {

    @GET("api/v1/menu")
    Observable<MenuRespon> getCityList();

}
