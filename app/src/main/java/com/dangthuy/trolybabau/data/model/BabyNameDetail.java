package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameDetail {
    private String lastName;
    private String firstName;
    private int tab;

    public BabyNameDetail(String lastName, String firstName, int tab) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.tab = tab;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getTab() {
        return tab;
    }
}
