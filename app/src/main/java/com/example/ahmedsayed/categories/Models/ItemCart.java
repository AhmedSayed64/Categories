package com.example.ahmedsayed.categories.Models;

public class ItemCart {

    private String item_url;
    private String item_name;
    private double item_price;
    private int item_count;

    public ItemCart(String item_url, String item_name, double item_price, int item_count) {
        this.item_url = item_url;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_count = item_count;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
}
