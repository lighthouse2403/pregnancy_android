package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.BabyName;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 4/6/2021.
 */
public class BabyNameResponse extends BaseResponse{
    @SerializedName("baby_name")
    private List<BabyName> babyName;

    public List<BabyName> getBabyName() {
        return babyName;
    }
}
