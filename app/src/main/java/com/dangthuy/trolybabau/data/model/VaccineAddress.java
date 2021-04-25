package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class VaccineAddress {
    @SerializedName("address")
    private String address;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
