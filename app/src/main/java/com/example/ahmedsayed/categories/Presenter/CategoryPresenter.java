package com.example.ahmedsayed.categories.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.ahmedsayed.categories.Models.CategoryResponse;
import com.example.ahmedsayed.categories.Retrofit.APIInterface;
import com.example.ahmedsayed.categories.Retrofit.ApiClient;
import com.example.ahmedsayed.categories.Views.CategoryView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    Context context;
    CategoryView categoryView;

    public CategoryPresenter(Context context, CategoryView categoryView) {
        this.context = context;
        this.categoryView = categoryView;
    }

    public void getCategories() {

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("restaurantid", "1");
        Call<CategoryResponse> call = apiInterface.getCategory(map);
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                categoryView.showListCategory(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.d("CategoryFailure", call.request().toString());

            }
        });


    }
}
