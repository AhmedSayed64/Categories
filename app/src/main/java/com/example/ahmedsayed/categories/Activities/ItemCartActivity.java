package com.example.ahmedsayed.categories.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.ahmedsayed.categories.Adapter.CartItemAdapter;
import com.example.ahmedsayed.categories.Business.ListItemCart;
import com.example.ahmedsayed.categories.R;

public class ItemCartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartItemAdapter adapter;
    TextView total_all;
    ListItemCart listItemCart = new ListItemCart();
    double total_price;
    double res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cart);
        recyclerView = findViewById(R.id.cart_itemsRecycler);
        total_all = findViewById(R.id.total_all);
        for (int i = 0; i < listItemCart.get_CartItemsList().size(); i++) {

            total_price = listItemCart.get_CartItemsList().get(i).getItem_price() * listItemCart.get_CartItemsList().get(i).getItem_count();
            res += total_price;
        }
        total_all.setText("Total : " + String.valueOf(res));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new CartItemAdapter(this, listItemCart.get_CartItemsList());

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}
