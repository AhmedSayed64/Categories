package com.example.ahmedsayed.categories.Views;

import com.example.ahmedsayed.categories.Models.User;

public interface UserView {
    void OpenMain(User user);

    void ShowError(String error);
}
