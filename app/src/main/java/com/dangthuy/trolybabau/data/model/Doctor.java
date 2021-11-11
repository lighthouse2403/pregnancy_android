package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class Doctor implements Parcelable {
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("full_description")
    private String fullDescription;
    @SerializedName("id")
    private String id;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("rank")
    private String rank;
    @SerializedName("star")
    private String star;
    private int starRank;

    public Doctor() {
    }

    protected Doctor(Parcel in) {
        address = in.readString();
        city = in.readString();
        fullDescription = in.readString();
        id = in.readString();
        image = in.readString();
        name = in.readString();
        phone = in.readString();
        rank = in.readString();
        star = in.readString();
        starRank = in.readInt();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRank() {
        return rank;
    }

    public String getStar() {
        return star;
    }

    public String getCity() {
        return city;
    }

    public int getStarRank() {
        return starRank;
    }

    public void setStarRank(int starRank) {
        this.starRank = starRank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(fullDescription);
        parcel.writeString(id);
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(rank);
        parcel.writeString(star);
        parcel.writeInt(starRank);
    }
}
