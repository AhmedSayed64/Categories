package com.example.ahmedsayed.categories.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.ahmedsayed.categories.Models.ItemResponse;
import com.example.ahmedsayed.categories.Retrofit.APIInterface;
import com.example.ahmedsayed.categories.Retrofit.ApiClient;
import com.example.ahmedsayed.categories.Views.ItemView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemCatPresenter {

    Context context;
    ItemView itemView;
    int id;

    public ItemCatPresenter(Context context, ItemView itemView, int id) {
        this.context = context;
        this.itemView = itemView;
        this.id = id;
    }

    public void getItems() {

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("restaurantid", "1");
        map.put("categoryId", String.valueOf(id));
        Call<ItemResponse> call = apiInterface.getCatItems(map);
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                itemView.showList(response.body().getItems());
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                Log.d("CategoryFailure", call.request().toString());

            }
        });

    }
}