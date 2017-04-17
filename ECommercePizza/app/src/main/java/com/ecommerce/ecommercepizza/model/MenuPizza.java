package com.ecommerce.ecommercepizza.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Asus on 14/04/2017.
 */
@DatabaseTable(tableName = MenuPizza.tbl_menu_pizza)
public class MenuPizza implements Serializable {
    public static final String tbl_menu_pizza = "TBL_MENU_PIZZA";
    public static final String clm_id_menu = "ID_MENU";
    public static final String clm_menu_name = "NAME";
    public static final String clm_detail = "DETAIL";
    public static final String clm_harga = "HARGA";
    public static final String clm_size = "SIZE";

    public MenuPizza(){
    }

    public MenuPizza(String idMenu, String menuName, String detail, String harga, String size) {
        this.idMenu = idMenu;
        this.menuName = menuName;
        this.detail = detail;
        this.harga = harga;
        this.size = size;
    }

    @DatabaseField(id = true, columnName = clm_id_menu)
    String idMenu;
    @DatabaseField(columnName = clm_menu_name)
    String menuName;
    @DatabaseField(columnName = clm_detail)
    String detail;
    @DatabaseField(columnName = clm_harga)
    String harga;
    @DatabaseField(columnName = clm_size)
    String size;

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
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
