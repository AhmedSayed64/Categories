package com.example.ahmedsayed.categories.Presenter;

import android.content.Context;

import com.example.ahmedsayed.categories.Models.City;
import com.example.ahmedsayed.categories.Models.LoginResponse;
import com.example.ahmedsayed.categories.Models.RegisterResponse;
import com.example.ahmedsayed.categories.Models.User;
import com.example.ahmedsayed.categories.Retrofit.APIInterface;
import com.example.ahmedsayed.categories.Retrofit.ApiClient;
import com.example.ahmedsayed.categories.Views.UserView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {

    private Context context;
    private UserView userView;

    public UserPresenter(Context context, UserView userView) {
        this.context = context;
        this.userView = userView;
    }


    public void login(String nameORmail, String pass) {
        Map<String, String> map = new HashMap<>();
        map.put("emailOrPhone", nameORmail);
        map.put("password", pass);
        map.put("token", "1");
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<LoginResponse> call = apiInterface.login(map);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("ok")) {

                        User user = new User();
                        user.setName(response.body().getArabicName().toString());
                        user.setUrl(response.body().getPic());
                        user.setId(response.body().getUserid());
                        userView.OpenMain(user);


                    } else {
                        userView.ShowError("Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    public void register(final User user, City city) {

        Map<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("password", user.getPassword());
        map.put("mail", user.getMail());
        map.put("gender", user.getGender());
        map.put("cityId", city.getId().toString());
        map.put("districtId", "1");
        map.put("address", user.getAddress());
        map.put("phone", user.getPhone());

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);

        retrofit2.Call<RegisterResponse> call = apiInterface.register(map);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("ok")) {
                        userView.OpenMain(user);
                    } else {
                        userView.ShowError("Error");
                    }
                }

            }

            @Override
            public void onFailure(retrofit2.Call<RegisterResponse> call, Throwable t) {
                userView.ShowError("ErrorFailed");

            }
        });

    }
}
