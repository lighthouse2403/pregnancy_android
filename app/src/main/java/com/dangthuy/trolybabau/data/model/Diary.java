package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class Diary {
    private String title;
    private String content;
    private String createdDate;

    public Diary() {
    }

    public Diary(String title, String content, String createdDate) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}
