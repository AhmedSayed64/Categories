package com.example.ahmedsayed.categories.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ahmedsayed.categories.Business.ListItemCart;
import com.example.ahmedsayed.categories.Interface.counter_cartIterface;
import com.example.ahmedsayed.categories.Models.Item;
import com.example.ahmedsayed.categories.Models.ItemCart;
import com.example.ahmedsayed.categories.R;
import com.example.ahmedsayed.categories.Views.ItemView;

import java.util.List;

public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.ViewHolder> {

    Context context;
    List<Item> items;
    ItemView itemView;
    int counter = 0;
    counter_cartIterface iterface;

    public void setLiset(counter_cartIterface myInterface) {
        this.iterface = myInterface;
    }


    public CategoryItemsAdapter(Context context, List<Item> items, ItemView itemView) {
        this.context = context;
        this.items = items;
        this.itemView = itemView;
    }

    @Override
    public CategoryItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.cat_item_row, parent, false);
        return new CategoryItemsAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final CategoryItemsAdapter.ViewHolder holder, final int position) {

        final Item item = items.get(position);

        holder.item_name.setText(item.getArabicName());
        Glide.with(context).load(item.getImageUrl()).into(holder.item_img);
        holder.price.setText(item.getPrice().toString());
        item.setItem_countity(1);
        holder.counter.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                item.setItem_countity(newValue);

            }
        });


        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.counter.setNumber("1");
                counter++;
                iterface.counter_cart(counter);
                ItemCart itemCart = new ItemCart(item.getImageUrl(), item.getArabicName(), item.getPrice(), item.getItem_countity());
                ListItemCart listItemCart = new ListItemCart();
                listItemCart.set_listItems(itemCart);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cart, item_img;
        TextView item_name, price;
        ElegantNumberButton counter;

        public ViewHolder(View itemView) {
            super(itemView);

            counter = itemView.findViewById(R.id.counter_item);
            cart = itemView.findViewById(R.id.cart);
            item_name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            item_img = itemView.findViewById(R.id.item_img);

        }
    }

}
