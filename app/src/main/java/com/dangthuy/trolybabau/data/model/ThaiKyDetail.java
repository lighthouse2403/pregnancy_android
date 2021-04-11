package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class ThaiKyDetail {
    private final String title;
    private final String content;

    public ThaiKyDetail(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
