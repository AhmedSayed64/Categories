package com.example.ahmedsayed.categories.Views;

import com.example.ahmedsayed.categories.Models.Item;
import com.example.ahmedsayed.categories.Models.ItemCart;

import java.util.List;

public interface ItemView {
    void showList(List<Item> items);

    void showItemCart(ItemCart item);


}
