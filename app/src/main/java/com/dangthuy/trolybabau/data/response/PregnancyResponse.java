package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Pregnancy;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class PregnancyResponse extends BaseResponse{
    @SerializedName("pregnancy")
    private List<Pregnancy> pregnancy;

    public List<Pregnancy> getPregnancy() {
        return pregnancy;
    }
}
