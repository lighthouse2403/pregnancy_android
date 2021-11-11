package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 11/8/2021.
 */

public class DoctorComment {
    @SerializedName("like")
    private String like;
    @SerializedName("time")
    private Long time;
    @SerializedName("userName")
    private String userName;
    @SerializedName("content")
    private String content;

    public String getLike() {
        return like;
    }

    public Long getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }
}
