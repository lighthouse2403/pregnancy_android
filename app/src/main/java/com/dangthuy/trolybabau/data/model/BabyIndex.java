package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class BabyIndex implements Parcelable {
    @SerializedName("week")
    private String week;
    @SerializedName("bpd_tb")
    private String bpdTb;
    @SerializedName("bpd_gh")
    private String bpdGh;
    @SerializedName("fl_tb")
    private String flTb;
    @SerializedName("fl_gh")
    private String flGh;
    @SerializedName("efw_tb")
    private String efwTb;
    @SerializedName("efw_gh")
    private String efwGh;

    protected BabyIndex(Parcel in) {
        week = in.readString();
        bpdTb = in.readString();
        bpdGh = in.readString();
        flTb = in.readString();
        flGh = in.readString();
        efwTb = in.readString();
        efwGh = in.readString();
    }

    public static final Creator<BabyIndex> CREATOR = new Creator<BabyIndex>() {
        @Override
        public BabyIndex createFromParcel(Parcel in) {
            return new BabyIndex(in);
        }

        @Override
        public BabyIndex[] newArray(int size) {
            return new BabyIndex[size];
        }
    };

    public String getWeek() {
        return week;
    }

    public String getBpdTb() {
        return bpdTb;
    }

    public String getBpdGh() {
        return bpdGh;
    }

    public String getFlTb() {
        return flTb;
    }

    public String getFlGh() {
        return flGh;
    }

    public String getEfwTb() {
        return efwTb;
    }

    public String getEfwGh() {
        return efwGh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(week);
        parcel.writeString(bpdTb);
        parcel.writeString(bpdGh);
        parcel.writeString(flTb);
        parcel.writeString(flGh);
        parcel.writeString(efwTb);
        parcel.writeString(efwGh);
    }
}
