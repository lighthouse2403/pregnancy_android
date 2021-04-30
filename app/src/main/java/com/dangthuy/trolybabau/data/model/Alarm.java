package com.dangthuy.trolybabau.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by nhongthai on 4/30/2021.
 */
@Entity
public class Alarm implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "year")
    public int year;
    @ColumnInfo(name = "month")
    public int month;
    @ColumnInfo(name = "dayOfMonth")
    public int dayOfMonth;
    @ColumnInfo(name = "hour")
    public int hour;
    @ColumnInfo(name = "min")
    public int min;
    @ColumnInfo(name = "note")
    public String note;
    @ColumnInfo(name = "status")
    public boolean status;

    public Alarm(int year, int month, int dayOfMonth, int hour, int min, String note, boolean status) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.min = min;
        this.note = note;
        this.status = status;
    }

    protected Alarm(Parcel in) {
        year = in.readInt();
        month = in.readInt();
        dayOfMonth = in.readInt();
        hour = in.readInt();
        min = in.readInt();
        note = in.readString();
        status = in.readByte() != 0;
    }

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public String getNote() {
        return note;
    }

    public boolean isStatus() {
        return status;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(year);
        parcel.writeInt(month);
        parcel.writeInt(dayOfMonth);
        parcel.writeInt(hour);
        parcel.writeInt(min);
        parcel.writeString(note);
        parcel.writeByte((byte) (status ? 1 : 0));
    }
}
