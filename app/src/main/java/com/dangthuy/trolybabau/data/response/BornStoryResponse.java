package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.BornStory;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class BornStoryResponse extends BaseResponse {
    @SerializedName("data")
    private List<BornStory> data;

    public List<BornStory> getData() {
        return data;
    }
}
