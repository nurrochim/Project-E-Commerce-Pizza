package com.ecommerce.ecommerpizzas.view.fragment;

import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;
import com.ecommerce.ecommerpizzas.models.menu.MenuRespon;

/**
 * Created by Asus on 21/05/2017.
 */

public interface MenuFragment {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getMenuListSuccess(MenuRespon menuRespon);
}
