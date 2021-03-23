package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class Share implements Parcelable {
    private String title;
    private String name;
    private int viewCount;
    private int commentCount;
    private String createdDate;

    protected Share(Parcel in) {
        title = in.readString();
        name = in.readString();
        viewCount = in.readInt();
        commentCount = in.readInt();
        createdDate = in.readString();
    }

    public Share(String title) {
        this.title = title;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(name);
        parcel.writeInt(viewCount);
        parcel.writeInt(commentCount);
        parcel.writeString(createdDate);
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}
