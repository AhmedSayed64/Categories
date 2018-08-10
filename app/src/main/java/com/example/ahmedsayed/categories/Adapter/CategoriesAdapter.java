package com.example.ahmedsayed.categories.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmedsayed.categories.Activities.CategoryItems_Activity;
import com.example.ahmedsayed.categories.Models.Category;
import com.example.ahmedsayed.categories.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    Context context;
    List<Category> categories;

    public CategoriesAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Category category = categories.get(position);
        holder.cat_name.setText(category.getArabicName());
        Glide.with(context).load(category.getImage()).into(holder.cat_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryItems_Activity.class);
                intent.putExtra("EXTRA_ID", categories.get(position).getCategoryId());

                Log.d("poss", String.valueOf(position));

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cat_img;
        TextView cat_name;

        public ViewHolder(View itemView) {
            super(itemView);
            cat_img = itemView.findViewById(R.id.cat_img);
            cat_name = itemView.findViewById(R.id.cat_name);
        }
    }
}
