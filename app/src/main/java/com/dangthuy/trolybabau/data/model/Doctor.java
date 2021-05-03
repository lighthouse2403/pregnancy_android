package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class Doctor {
    @SerializedName("address")
    private String address;
    @SerializedName("full_description")
    private String fullDescription;
    @SerializedName("id")
    private String id;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("rank")
    private String rank;
    @SerializedName("star")
    private String star;

    public String getAddress() {
        return address;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getId() {
        return id;
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

    public String getRank() {
        return rank;
    }

    public String getStar() {
        return star;
    }
}
