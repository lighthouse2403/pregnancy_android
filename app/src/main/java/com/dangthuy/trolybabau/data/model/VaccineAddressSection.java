package com.dangthuy.trolybabau.data.model;

import java.util.List;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class VaccineAddressSection {
    private String section;
    private List<VaccineAddress> addresses;

    public String getSection() {
        return section;
    }

    public List<VaccineAddress> getAddresses() {
        return addresses;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setAddresses(List<VaccineAddress> addresses) {
        this.addresses = addresses;
    }
}
