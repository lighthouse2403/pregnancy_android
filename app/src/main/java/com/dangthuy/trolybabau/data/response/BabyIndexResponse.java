package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.data.response.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class BabyIndexResponse extends BaseResponse {
    @SerializedName("index_detail")
    private List<BabyIndex> details;

    public List<BabyIndex> getDetails() {
        return details;
    }
}
