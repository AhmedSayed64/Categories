package com.example.ahmedsayed.categories.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ahmedsayed.categories.Adapter.CategoriesAdapter;
import com.example.ahmedsayed.categories.Business.ListItemCart;
import com.example.ahmedsayed.categories.Models.Category;
import com.example.ahmedsayed.categories.Models.ItemCart;
import com.example.ahmedsayed.categories.Presenter.CategoryPresenter;
import com.example.ahmedsayed.categories.R;
import com.example.ahmedsayed.categories.Views.CategoryView;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements CategoryView {

    RecyclerView recyclerView;
    CategoriesAdapter adapter;
    List<ItemCart> cartList = new ArrayList<>();
    ListItemCart listItemCart = new ListItemCart();
    private TextView count_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        recyclerView = findViewById(R.id.categoryRecycler);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        CategoryPresenter presenter = new CategoryPresenter(this, this);
        presenter.getCategories();
    }

    @Override
    public void showListCategory(List<Category> categories) {
        adapter = new CategoriesAdapter(this, categories);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);


        View count = menu.findItem(R.id.cart_icon).getActionView();
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(CategoriesActivity.this, ItemCartActivity.class);
                cartList = listItemCart.get_CartItemsList();
                startActivity(cartIntent);
            }
        });
        count_TV = count.findViewById(R.id.hotlist_hot);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    protected void onResume() {
        cartList = listItemCart.get_CartItemsList();
        if (cartList.size() != 0) {
            count_TV.setText(String.valueOf(cartList.size()));
        }
        super.onResume();
    }


}
