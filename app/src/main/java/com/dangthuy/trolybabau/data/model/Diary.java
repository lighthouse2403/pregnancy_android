package com.dangthuy.trolybabau.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
@Entity
public class Diary {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "content")
    public String content;
    @ColumnInfo(name = "createdDate")
    public String createdDate;
    @ColumnInfo(name = "images")
    public String images;

    public Diary(String title, String content, String createdDate, String images) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.images = images;
    }
}
