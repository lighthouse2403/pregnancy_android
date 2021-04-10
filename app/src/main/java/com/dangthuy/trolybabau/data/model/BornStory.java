package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 3/25/2021.
 */
public class BornStory implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    protected BornStory(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<BornStory> CREATOR = new Creator<BornStory>() {
        @Override
        public BornStory createFromParcel(Parcel in) {
            return new BornStory(in);
        }

        @Override
        public BornStory[] newArray(int size) {
            return new BornStory[size];
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
