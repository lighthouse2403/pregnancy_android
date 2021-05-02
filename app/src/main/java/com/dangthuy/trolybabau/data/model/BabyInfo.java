package com.dangthuy.trolybabau.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by nhongthai on 3/29/2021.
 */
@Entity
public class BabyInfo {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "year")
    public int year;
    @ColumnInfo(name = "month")
    public int month;
    @ColumnInfo(name = "dayOfMonth")
    public int dayOfMonth;
    @ColumnInfo(name = "weight")
    public String weight;
    @ColumnInfo(name = "images")
    public String images;
    @ColumnInfo(name = "week")
    public int week;
    @ColumnInfo(name = "day")
    public int day;

    public BabyInfo(int year, int month, int dayOfMonth, String weight, String images, int week, int day) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.weight = weight;
        this.images = images;
        this.week = week;
        this.day = day;
    }
}
