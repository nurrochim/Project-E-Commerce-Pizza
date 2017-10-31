package com.ecommerce.ecommerpizzas.models.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Asus on 17/04/2017.
 */

@DatabaseTable(tableName = MyCart.tbl_mycart)
public class MyCart implements Serializable {

    public static final String tbl_mycart = "TBL_MY_CART";
    public static final String clm_id = "ID";
    public static final String clm_fidMenu = "FID_MENU";
    public static final String clm_menu_name = "NAME";
    public static final String clm_detail = "DETAIL";
    public static final String clm_harga = "HARGA";
    public static final String clm_size = "SIZE";
    public static final String clm_qty = "QTY";
    public static final String clm_image = "IMAGE";

    public MyCart() {
    }

    public MyCart(int fidMenuPizza, String menuName, String detail, String harga, String size, String qty, String image) {
        this.fidMenuPizza = fidMenuPizza;
        this.menuName = menuName;
        this.detail = detail;
        this.harga = harga;
        this.size = size;
        this.qty = qty;
        this.image = image;
    }

    @DatabaseField(generatedId = true, columnName = clm_id)
    Integer idPesanan;

    @DatabaseField(columnName = clm_fidMenu)
    Integer fidMenuPizza;
    @DatabaseField(columnName = clm_menu_name)
    String menuName;
    @DatabaseField(columnName = clm_detail)
    String detail;
    @DatabaseField(columnName = clm_harga)
    String harga;
    @DatabaseField(columnName = clm_size)
    String size;
    @DatabaseField(columnName = clm_qty)
    String qty;
    @DatabaseField(columnName = clm_image)
    String image;

    public Integer getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(Integer idPesanan) {
        this.idPesanan = idPesanan;
    }

    public static String getClm_fidMenu() {
        return clm_fidMenu;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getFidMenuPizza() {
        return fidMenuPizza;
    }

    public void setFidMenuPizza(Integer fidMenuPizza) {
        this.fidMenuPizza = fidMenuPizza;
    }
}
