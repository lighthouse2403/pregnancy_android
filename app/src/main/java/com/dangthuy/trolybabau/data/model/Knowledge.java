package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class Knowledge implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    protected Knowledge(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<Knowledge> CREATOR = new Creator<Knowledge>() {
        @Override
        public Knowledge createFromParcel(Parcel in) {
            return new Knowledge(in);
        }

        @Override
        public Knowledge[] newArray(int size) {
            return new Knowledge[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
    }
}
