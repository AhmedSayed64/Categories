package com.example.ahmedsayed.categories.Presenter;

import android.content.Context;

import com.example.ahmedsayed.categories.Models.CityResponse;
import com.example.ahmedsayed.categories.Retrofit.APIInterface;
import com.example.ahmedsayed.categories.Retrofit.ApiClient;
import com.example.ahmedsayed.categories.Views.CityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityPresenter {

    Context context;
    CityView cityView;


    public CityPresenter(Context context, CityView cityView) {
        this.cityView = cityView;
        this.context = context;
    }

    public void getCities() {
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<CityResponse> call = apiInterface.getCity();
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {

                cityView.showList(response.body().getCities());

            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {

            }
        });


    }
}
