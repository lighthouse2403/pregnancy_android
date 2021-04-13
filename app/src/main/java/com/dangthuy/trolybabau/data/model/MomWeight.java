package com.dangthuy.trolybabau.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by nhongthai on 3/29/2021.
 */
@Entity
public class MomWeight {
    @PrimaryKey(autoGenerate = true)
    public long uid;
    @ColumnInfo(name = "weight")
    public String weight;
    @ColumnInfo(name = "created_date")
    public String createdDate;
    @ColumnInfo(name = "week")
    public int week;
    @ColumnInfo(name = "day_of_week")
    public int dayOfWeek;

    public MomWeight(String weight, String createdDate, int week, int dayOfWeek) {
        this.weight = weight;
        this.createdDate = createdDate;
        this.week = week;
        this.dayOfWeek = dayOfWeek;
    }
}
