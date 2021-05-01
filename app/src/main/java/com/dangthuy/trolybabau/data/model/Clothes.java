package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class Clothes {
    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private String amount;

    public Clothes(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}
