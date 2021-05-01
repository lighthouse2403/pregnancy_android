package com.dangthuy.trolybabau.data.model;

import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class ClothesSection {
    private String section;
    private List<Clothes> data;

    public ClothesSection(String section, List<Clothes> data) {
        this.section = section;
        this.data = data;
    }

    public String getSection() {
        return section;
    }

    public List<Clothes> getData() {
        return data;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setData(List<Clothes> data) {
        this.data = data;
    }
}
