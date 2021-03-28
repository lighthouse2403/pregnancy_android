package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeMenu {
    private final String title;
    private final String image;

    public HomeMenu(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
