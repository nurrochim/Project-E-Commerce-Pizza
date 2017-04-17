package com.ecommerce.ecommercepizza.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Asus on 17/04/2017.
 */

public class MyCart implements Serializable {

    //@DatabaseField(id = true, columnName = clm_id_menu)
    String idPesanan;
    String fidMenuPizza;
    //@DatabaseField(columnName = clm_menu_name)
    String menuName;
    //@DatabaseField(columnName = clm_detail)
    String detail;
    //@DatabaseField(columnName = clm_harga)
    String harga;
    //@DatabaseField(columnName = clm_size)
    String size;
    String qty;

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getFidMenuPizza() {
        return fidMenuPizza;
    }

    public void setFidMenuPizza(String fidMenuPizza) {
        this.fidMenuPizza = fidMenuPizza;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
