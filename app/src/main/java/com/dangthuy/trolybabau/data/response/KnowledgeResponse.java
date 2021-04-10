package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Knowledge;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 4/10/2021.
 */
public class KnowledgeResponse extends BaseResponse{
    @SerializedName("data")
    private List<Knowledge> data;

    public List<Knowledge> getData() {
        return data;
    }
}
