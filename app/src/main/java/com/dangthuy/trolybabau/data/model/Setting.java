package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class Setting {
    public static final String WALLPAPER = "Ảnh nền";
    public static final String SHARE_APP = "Chia sẻ ứng dụng";
    public static final String VIP = "Ẩn quảng cáo";
    String title;
    String image;

    public Setting(String title, String image) {
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
