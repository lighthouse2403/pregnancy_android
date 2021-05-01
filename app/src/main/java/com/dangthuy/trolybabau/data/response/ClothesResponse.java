package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Clothes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class ClothesResponse extends BaseResponse{
    @SerializedName("summer_closet")
    private List<Clothes> summerCloset;
    @SerializedName("winter_closet")
    private List<Clothes> winterCloset;
    @SerializedName("diaper")
    private List<Clothes> diaper;
    @SerializedName("milk_bottle")
    private List<Clothes> milkBottle;
    @SerializedName("drug")
    private List<Clothes> drug;
    @SerializedName("mama_closet")
    private List<Clothes> mamaCloset;

    public List<Clothes> getSummerCloset() {
        return summerCloset;
    }

    public List<Clothes> getWinterCloset() {
        return winterCloset;
    }

    public List<Clothes> getDiaper() {
        return diaper;
    }

    public List<Clothes> getMilkBottle() {
        return milkBottle;
    }

    public List<Clothes> getDrug() {
        return drug;
    }

    public List<Clothes> getMamaCloset() {
        return mamaCloset;
    }
}
