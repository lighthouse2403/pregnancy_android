package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class Comment {
    @SerializedName("content")
    private String content;
    @SerializedName("like")
    private String like;
    @SerializedName("name")
    private String name;
    @SerializedName("time")
    private long time;
    @SerializedName("userName")
    private String userName;

    public String getContent() {
        return content;
    }

    public String getLike() {
        return like;
    }

    public String getName() {
        return name;
    }

    public Long getTime() {
        return time;
    }

    public String getUserName() {
        return userName;
    }
}
