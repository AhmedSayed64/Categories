package com.example.ahmedsayed.categories.Retrofit;


import com.example.ahmedsayed.categories.Models.CategoryResponse;
import com.example.ahmedsayed.categories.Models.CityResponse;
import com.example.ahmedsayed.categories.Models.ItemResponse;
import com.example.ahmedsayed.categories.Models.LoginResponse;
import com.example.ahmedsayed.categories.Models.RegisterResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIInterface {
    @POST("Login")
    Call<LoginResponse> login(@QueryMap Map<String, String> query);

    @POST("Register")
    Call<RegisterResponse> register(@QueryMap Map<String, String> query);

    @GET("getCities")
    Call<CityResponse> getCity();

    @POST("getCatagories")
    Call<CategoryResponse> getCategory(@QueryMap Map<String, String> query);

    @POST("GetcategoryItems")
    Call<ItemResponse> getCatItems(@QueryMap Map<String, String> query);


}
