package com.dangthuy.trolybabau.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Created by nhongthai on 4/11/2021.
 */
@Entity
public class BabyFoot {
    @PrimaryKey(autoGenerate = true)
    public long uid;
    @ColumnInfo(name = "foot")
    public int foot;
    @ColumnInfo(name = "created_date")
    public String createdDate;
    @ColumnInfo(name = "hour")
    public int hour;
    @ColumnInfo(name = "minute")
    public int minute;
    @ColumnInfo(name = "second")
    public int second;

    public BabyFoot(int foot, String createdDate, int hour, int minute, int second) {
        this.foot = foot;
        this.createdDate = createdDate;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}
