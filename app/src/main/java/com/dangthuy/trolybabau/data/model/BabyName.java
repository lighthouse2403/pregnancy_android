package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 4/6/2021.
 */
public class BabyName {
    @SerializedName("first_character")
    private String firstCharacter;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("first_name_girl")
    private String firstNameGirl;
    @SerializedName("first_name_boy")
    private String firstNameBoy;

    public String getFirstCharacter() {
        return firstCharacter;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstNameGirl() {
        return firstNameGirl;
    }

    public String getFirstNameBoy() {
        return firstNameBoy;
    }
}
