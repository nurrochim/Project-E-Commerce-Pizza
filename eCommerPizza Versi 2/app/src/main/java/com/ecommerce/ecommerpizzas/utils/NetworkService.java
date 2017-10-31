package com.ecommerce.ecommerpizzas.utils;


import com.ecommerce.ecommerpizzas.models.menu.MenuRespon;
import com.ecommerce.ecommerpizzas.models.post.PostEmployee;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface NetworkService {

    @GET("api/v1/menu")
    Observable<MenuRespon> getCityList();

    @POST("api/v1/employees")
    @FormUrlEncoded
    Call<String> savePost(@Field("name") String name,
                                @Field("email") String email,
                                @Field("contact_number") String contactNumber,
                                @Field("position") String position);

    @POST("api/v1/order_header")
    @FormUrlEncoded
    Call<String> postHeader(@Field("id_order") int idOrder,
                            @Field("name") String name,
                            @Field("email") String email,
                            @Field("contact_number") String contactNumber,
                            @Field("address") String address,
                            @Field("status_order") String statusOrder,
                            @Field("status_order_desc") String statusOrderDesc,
                            @Field("delivery_fee") String delveryFee,
                            @Field("sub_total") String subTotal,
                            @Field("total") String total,
                            @Field("token") String token,
                            @Field("paymentMethod") String paymentMethod
                            );

    @POST("api/v1/order_detail")
    @FormUrlEncoded
    Call<String> postDetailOrder(@Field("fid_order") int idOrder,
                                 @Field("name_menu") String menuName,
                                 @Field("detail") String detail,
                                 @Field("harga") String harga,
                                 @Field("size") String size,
                                 @Field("image_path_h") String imageH,
                                 @Field("image_path_d") String imageD,
                                 @Field("qty") String qty,
                                 @Field("sub_total") String subTotal
                                 );

}
