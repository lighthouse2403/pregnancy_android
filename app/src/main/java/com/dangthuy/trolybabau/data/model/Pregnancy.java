package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class Pregnancy implements Parcelable {
    @SerializedName("week")
    private String week;
    @SerializedName("mom")
    private String mom;
    @SerializedName("baby")
    private String baby;
    @SerializedName("advice")
    private String advice;

    protected Pregnancy(Parcel in) {
        week = in.readString();
        mom = in.readString();
        baby = in.readString();
        advice = in.readString();
    }

    public static final Creator<Pregnancy> CREATOR = new Creator<Pregnancy>() {
        @Override
        public Pregnancy createFromParcel(Parcel in) {
            return new Pregnancy(in);
        }

        @Override
        public Pregnancy[] newArray(int size) {
            return new Pregnancy[size];
        }
    };

    public String getWeek() {
        return week;
    }

    public String getMom() {
        return mom;
    }

    public String getBaby() {
        return baby;
    }

    public String getAdvice() {
        return advice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(week);
        parcel.writeString(mom);
        parcel.writeString(baby);
        parcel.writeString(advice);
    }
}
