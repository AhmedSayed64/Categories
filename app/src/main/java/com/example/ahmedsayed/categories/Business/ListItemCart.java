package com.example.ahmedsayed.categories.Business;

import com.example.ahmedsayed.categories.Models.ItemCart;

import java.util.ArrayList;
import java.util.List;

public class ListItemCart {

    static List<ItemCart> list = new ArrayList<>();

    public void set_listItems(ItemCart itemCart) {

        list.add(itemCart);
    }

    public List<ItemCart> get_CartItemsList() {


        return list;
    }

}
