package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class Nutri {
    @SerializedName("name")
    private String name;
    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("full_description")
    private String fullDescription;
    @SerializedName("image")
    private String image;

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getImage() {
        return image;
    }
}
