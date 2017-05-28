package com.ecommerce.ecommerpizzas.models.menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * Created by Nurochim on 25/05/2017.
 */

@Generated("org.jsonschema2pojo")
public class MenuRespon {
    @SerializedName("data")
    @Expose
    private List<MenuListData> data = new ArrayList<MenuListData>();

    public List<MenuListData> getData() {
        return data;
    }

    public void setData(List<MenuListData> data) {
        this.data = data;
    }
}
