package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class Share implements Parcelable {
    @SerializedName("content")
    private String content;
    @SerializedName("lastComment")
    private long lastComment;
    @SerializedName("numberOfComment")
    private int numberOfComment;
    @SerializedName("owner")
    private String owner;
    @SerializedName("time")
    private long time;
    @SerializedName("title")
    private String title;
    @SerializedName("userName")
    private String userName;
    @SerializedName("views")
    private int views;
    private String key;

    public Share() {
    }

    protected Share(Parcel in) {
        content = in.readString();
        lastComment = in.readLong();
        numberOfComment = in.readInt();
        owner = in.readString();
        time = in.readLong();
        title = in.readString();
        userName = in.readString();
        views = in.readInt();
        key = in.readString();
    }

    public static final Creator<Share> CREATOR = new Creator<Share>() {
        @Override
        public Share createFromParcel(Parcel in) {
            return new Share(in);
        }

        @Override
        public Share[] newArray(int size) {
            return new Share[size];
        }
    };

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getContent() {
        return content;
    }

    public long getLastComment() {
        return lastComment;
    }

    public Integer getNumberOfComment() {
        return numberOfComment;
    }

    public String getOwner() {
        return owner;
    }

    public long getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getViews() {
        return views;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeLong(lastComment);
        parcel.writeInt(numberOfComment);
        parcel.writeString(owner);
        parcel.writeLong(time);
        parcel.writeString(title);
        parcel.writeString(userName);
        parcel.writeInt(views);
        parcel.writeString(key);
    }
}
