package com.dangthuy.trolybabau.data.model;

import android.graphics.drawable.Drawable;

import androidx.annotation.IdRes;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeMenu {
    private final String title;
    private final Drawable image;

    public HomeMenu(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getImage() {
        return image;
    }
}
