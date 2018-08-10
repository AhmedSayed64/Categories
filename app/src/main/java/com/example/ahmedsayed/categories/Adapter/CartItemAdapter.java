package com.example.ahmedsayed.categories.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ahmedsayed.categories.Models.ItemCart;
import com.example.ahmedsayed.categories.R;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    Context context;
    List<ItemCart> itemCartList;

    public CartItemAdapter(Context context, List<ItemCart> itemCartList) {
        this.context = context;
        this.itemCartList = itemCartList;
    }

    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.cart_row, parent, false);
        return new CartItemAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartItemAdapter.ViewHolder holder, int position) {

        final ItemCart itemCart = itemCartList.get(position);

        holder.item_name.setText(itemCart.getItem_name());
        Glide.with(context).load(itemCart.getItem_url()).into(holder.item_img);
        holder.price.setText("سعر القطعه : " + String.valueOf(itemCart.getItem_price()));
        holder.quant.setText("الكميه : " + String.valueOf(itemCart.getItem_count()));
        double t_price = itemCart.getItem_price() * itemCart.getItem_count();
        holder.total_price.setText("الاجمالي : " + String.valueOf(t_price));
        holder.counter.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                itemCart.setItem_count(newValue);
                holder.quant.setText("الكميه : " + String.valueOf(itemCart.getItem_count()));
                holder.total_price.setText("الاجمالي : " + String.valueOf(itemCart.getItem_price() * itemCart.getItem_count()));


            }
        });

    }

    @Override
    public int getItemCount() {
        return itemCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_img;
        TextView item_name, price, quant, total_price;
        ElegantNumberButton counter;

        public ViewHolder(View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name_cart);
            item_img = itemView.findViewById(R.id.item_img_cart);
            price = itemView.findViewById(R.id.item_price_cart);
            quant = itemView.findViewById(R.id.quantity);
            total_price = itemView.findViewById(R.id.total_peice);
            counter = itemView.findViewById(R.id.counter_item_cart);


        }
    }
}
