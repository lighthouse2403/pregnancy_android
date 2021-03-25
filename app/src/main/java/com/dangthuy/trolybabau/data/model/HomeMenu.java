package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeMenu {
    public static final String GOC_CHIA_SE = "Góc chia sẻ";
    public static final String CAU_CHUYEN_SINH_NO = "Câu chuyện sinh nở";
    public static final String NHAC_BAU_CHO_BE = "Nhạc bầu cho bé";
    private String title;
    private String image;

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
