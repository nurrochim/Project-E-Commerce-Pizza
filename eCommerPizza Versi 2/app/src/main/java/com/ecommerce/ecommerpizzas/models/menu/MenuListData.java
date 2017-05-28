package com.ecommerce.ecommerpizzas.models.menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by Nurochim on 25/05/2017.
 */

@Generated("org.jsonschema2pojo")
public class MenuListData {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("menuName")
    @Expose
    String menuName;

    @SerializedName("menuDetail")
    @Expose
    String menuDetail;

    @SerializedName("menuHarga")
    @Expose
    String menuHarga;

    @SerializedName("menuSize")
    @Expose
    String menuSize;

    @SerializedName("image1")
    @Expose
    String image1;

    @SerializedName("image2")
    @Expose
    String image2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDetail() {
        return menuDetail;
    }

    public void setMenuDetail(String menuDetail) {
        this.menuDetail = menuDetail;
    }

    public String getMenuHarga() {
        return menuHarga;
    }

    public void setMenuHarga(String menuHarga) {
        this.menuHarga = menuHarga;
    }

    public String getMenuSize() {
        return menuSize;
    }

    public void setMenuSize(String menuSize) {
        this.menuSize = menuSize;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
