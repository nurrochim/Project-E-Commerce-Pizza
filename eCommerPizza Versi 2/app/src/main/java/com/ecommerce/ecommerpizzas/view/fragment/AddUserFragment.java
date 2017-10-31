package com.ecommerce.ecommerpizzas.view.fragment;

/**
 * Created by Asus on 10/06/2017.
 */

public interface AddUserFragment{
        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);
}
