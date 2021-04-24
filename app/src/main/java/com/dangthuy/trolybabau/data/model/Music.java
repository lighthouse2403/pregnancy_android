package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 3/25/2021.
 */
public class Music {
    private String title;
    private String link;

    public Music(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
