package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class Nutri implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("full_description")
    private String fullDescription;
    @SerializedName("image")
    private String image;

    protected Nutri(Parcel in) {
        name = in.readString();
        shortDescription = in.readString();
        fullDescription = in.readString();
        image = in.readString();
    }

    public static final Creator<Nutri> CREATOR = new Creator<Nutri>() {
        @Override
        public Nutri createFromParcel(Parcel in) {
            return new Nutri(in);
        }

        @Override
        public Nutri[] newArray(int size) {
            return new Nutri[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(shortDescription);
        parcel.writeString(fullDescription);
        parcel.writeString(image);
    }
}
