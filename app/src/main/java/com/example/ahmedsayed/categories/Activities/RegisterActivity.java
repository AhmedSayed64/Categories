package com.example.ahmedsayed.categories.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmedsayed.categories.Models.City;
import com.example.ahmedsayed.categories.Models.User;
import com.example.ahmedsayed.categories.Presenter.CityPresenter;
import com.example.ahmedsayed.categories.Presenter.UserPresenter;
import com.example.ahmedsayed.categories.R;
import com.example.ahmedsayed.categories.Views.CityView;
import com.example.ahmedsayed.categories.Views.UserView;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.HashMap;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements UserView, CityView {
    UserPresenter userPresenter;
    CityPresenter cityPresenter;
    EditText address, mail, name, phone, password;
    Spinner citySpinner;
    CheckBox maleCheckBox, femaleCheckBox;
    Button signup;
    String gender;
    HashMap<Integer, String> spinnerMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        address = findViewById(R.id.addressET);
        mail = findViewById(R.id.mailET);
        name = findViewById(R.id.nameET);
        phone = findViewById(R.id.phoneET);
        password = findViewById(R.id.passwordET);
        citySpinner = findViewById(R.id.city_spinner);
        signup = findViewById(R.id.signup_btn);
        maleCheckBox = findViewById(R.id.male_CB);
        femaleCheckBox = findViewById(R.id.femaleCB);
        maleCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "0";
                femaleCheckBox.setChecked(false);
            }
        });
        femaleCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "1";
                maleCheckBox.setChecked(false);
            }
        });

        userPresenter = new UserPresenter(this, this);
        cityPresenter = new CityPresenter(this, this);
        cityPresenter.getCities();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FUtilsValidation.isEmpty(address, "please insert Address");
                FUtilsValidation.isEmpty(mail, "please insert Mail");
                FUtilsValidation.isEmpty(name, "please insert Name");
                FUtilsValidation.isEmpty(phone, "please insert Phone");
                FUtilsValidation.isEmpty(password, "please insert Password");
                FUtilsValidation.isLengthCorrect(password.getText().toString(), 8, 16);
                if (!FUtilsValidation.isLengthCorrect(password.getText().toString(), 8, 16))
                    password.setError("password min 8 char");

                if (!address.getText().toString().equals("") && !mail.getText().toString().equals("") && !name.getText().toString().equals("") && !phone.getText().toString().equals("") &&
                        (maleCheckBox.isChecked() || femaleCheckBox.isChecked()) && FUtilsValidation.isLengthCorrect(password.getText().toString(), 8, 16)) {
                    User user = new User();
                    final City city = new City();

                    user.setAddress(address.getText().toString());
                    user.setMail(mail.getText().toString());
                    user.setName(name.getText().toString());
                    user.setPhone(phone.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setGender(gender);
                    city.setId(Integer.valueOf(spinnerMap.get(citySpinner.getSelectedItemPosition())));

                    userPresenter.register(user, city);
                }
            }


        });


    }

    @Override
    public void OpenMain(User user) {
        Toast.makeText(getApplicationContext(), "User has been registered !", Toast.LENGTH_LONG).show();

    }

    @Override
    public void ShowError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showList(List<City> cities) {
        String[] spinnerArray = new String[cities.size()];

        for (int i = 0; i < cities.size(); i++) {
            spinnerMap.put(i, cities.get(i).getId().toString());
            spinnerArray[i] = cities.get(i).getEnglishName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);
    }
}
