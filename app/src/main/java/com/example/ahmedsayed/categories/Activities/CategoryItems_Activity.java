package com.example.ahmedsayed.categories.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ahmedsayed.categories.Adapter.CategoryItemsAdapter;
import com.example.ahmedsayed.categories.Interface.counter_cartIterface;
import com.example.ahmedsayed.categories.Models.Item;
import com.example.ahmedsayed.categories.Models.ItemCart;
import com.example.ahmedsayed.categories.Presenter.ItemCatPresenter;
import com.example.ahmedsayed.categories.R;
import com.example.ahmedsayed.categories.Views.ItemView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryItems_Activity extends AppCompatActivity implements ItemView, counter_cartIterface {

    int item_id;
    RecyclerView recyclerView;
    CategoryItemsAdapter adapter;
    private TextView count_TV;
    List<ItemCart> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item_id = extras.getInt("EXTRA_ID");
        }

        recyclerView = findViewById(R.id.categoryRecyclerItems);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        ItemCatPresenter presenter = new ItemCatPresenter(this, this, item_id);
        presenter.getItems();
    }

    @Override
    public void showList(List<Item> items) {

        adapter = new CategoryItemsAdapter(this, items, this);
        adapter.setLiset(CategoryItems_Activity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void showItemCart(ItemCart item) {
        cartList.add(item);
        Log.d("size", String.valueOf(cartList.size()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);


        View count = menu.findItem(R.id.cart_icon).getActionView();
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(CategoryItems_Activity.this, ItemCartActivity.class);
                cartIntent.putExtra("ExtraCartList", (Serializable) cartList);
                startActivity(cartIntent);
            }
        });
        count_TV = count.findViewById(R.id.hotlist_hot);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public void counter_cart(int counter) {
        count_TV.setText(String.valueOf(counter));

    }
}
