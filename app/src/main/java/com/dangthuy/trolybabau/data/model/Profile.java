package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 6/9/2021.
 */
public class Profile {
    private String momName;
    private String babyName;
    private String babyWeight;
    private String babyExpect;
    private String babyAge;
    private String kykinhcuoi;

    public Profile(String momName, String babyName, String babyWeight, String babyExpect, String babyAge, String kykinhcuoi) {
        this.momName = momName;
        this.babyName = babyName;
        this.babyWeight = babyWeight;
        this.babyExpect = babyExpect;
        this.babyAge = babyAge;
        this.kykinhcuoi = kykinhcuoi;
    }

    public String getMomName() {
        return momName;
    }

    public String getBabyName() {
        return babyName;
    }

    public String getBabyWeight() {
        return babyWeight;
    }

    public String getBabyExpect() {
        return babyExpect;
    }

    public String getBabyAge() {
        return babyAge;
    }

    public String getKykinhcuoi() {
        return kykinhcuoi;
    }
}
