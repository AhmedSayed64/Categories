package com.example.ahmedsayed.categories.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ahmedsayed.categories.Models.User;
import com.example.ahmedsayed.categories.Presenter.UserPresenter;
import com.example.ahmedsayed.categories.R;
import com.example.ahmedsayed.categories.Views.UserView;

public class LoginActivity extends AppCompatActivity implements UserView {

    EditText name, pass;

    UserPresenter presenter;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.NameOrMailET);
        pass = findViewById(R.id.passwordLoginET);
        login_btn = findViewById(R.id.login2_btn);
        presenter = new UserPresenter(this, this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(name.getText().toString(), pass.getText().toString());

            }
        });
    }

    @Override
    public void OpenMain(User user) {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);

    }

    @Override
    public void ShowError(String error) {

    }
}
