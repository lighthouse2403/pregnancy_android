package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class Wallpaper {
    private int color;
    private boolean isSelect;

    public Wallpaper(int color, boolean isSelect) {
        this.color = color;
        this.isSelect = isSelect;
    }

    public int getColor() {
        return color;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
