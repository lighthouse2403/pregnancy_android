package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameDetail {
    private String lastName;
    private String firstName;
    private int status;

    public BabyNameDetail(String lastName, String firstName, int status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
