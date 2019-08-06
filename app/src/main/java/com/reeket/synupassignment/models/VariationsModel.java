package com.reeket.synupassignment.models;

import com.google.gson.annotations.SerializedName;

public class VariationsModel {

    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private int price;
    @SerializedName("default")
    private int defaultX;
    @SerializedName("id")
    private String id;
    @SerializedName("inStock")
    private int inStock;
    @SerializedName("isVeg")
    private int isVeg;

    public VariationsModel(String name, int price, int defaultX, String id, int inStock, int isVeg) {
        this.name = name;
        this.price = price;
        this.defaultX = defaultX;
        this.id = id;
        this.inStock = inStock;
        this.isVeg = isVeg;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public String getId() {
        return id;
    }

    public int getInStock() {
        return inStock;
    }

    public int getIsVeg() {
        return isVeg;
    }

}
