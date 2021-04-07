package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Nutri;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 4/7/2021.
 */
public class NutriResponse extends BaseResponse{
    @SerializedName("nutrition")
    private List<Nutri> nutrition;
    @SerializedName("fruit")
    private List<Nutri> fruits;
    @SerializedName("food")
    private List<Nutri> food;
    @SerializedName("vitamin")
    private List<Nutri> vitamin;

    public List<Nutri> getNutrition() {
        return nutrition;
    }

    public List<Nutri> getFruits() {
        return fruits;
    }

    public List<Nutri> getFood() {
        return food;
    }

    public List<Nutri> getVitamin() {
        return vitamin;
    }
}
