package com.dangthuy.trolybabau.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

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
    private String key;

    public Comment() {

    }

    public Comment(String content, String like, String name, long time, String userName) {
        this.content = content;
        this.like = like;
        this.name = name;
        this.time = time;
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
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

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("content", content);
        result.put("like", like);
        result.put("name", name);
        result.put("time", time);
        result.put("userName", userName);
        return result;
    }
}
