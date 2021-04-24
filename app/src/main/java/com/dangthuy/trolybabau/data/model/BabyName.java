package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 4/6/2021.
 */
public class BabyName implements Parcelable {
    @SerializedName("first_character")
    private String firstCharacter;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("first_name_girl")
    private String firstNameGirl;
    @SerializedName("first_name_boy")
    private String firstNameBoy;

    protected BabyName(Parcel in) {
        firstCharacter = in.readString();
        lastName = in.readString();
        firstNameGirl = in.readString();
        firstNameBoy = in.readString();
    }

    public static final Creator<BabyName> CREATOR = new Creator<BabyName>() {
        @Override
        public BabyName createFromParcel(Parcel in) {
            return new BabyName(in);
        }

        @Override
        public BabyName[] newArray(int size) {
            return new BabyName[size];
        }
    };

    public String getFirstCharacter() {
        return firstCharacter;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstNameGirl() {
        return firstNameGirl;
    }

    public String getFirstNameBoy() {
        return firstNameBoy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstCharacter);
        parcel.writeString(lastName);
        parcel.writeString(firstNameGirl);
        parcel.writeString(firstNameBoy);
    }
}
