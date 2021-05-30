package com.dangthuy.trolybabau.data.model;

/**
 * Created by nhongthai on 5/29/2021.
 */
public class Chart {
    private float[] lowValues;
    private float[] hightValues;
    private String[] dates;

    public Chart(float[] lowValues, float[] hightValues, String[] dates) {
        this.lowValues = lowValues;
        this.hightValues = hightValues;
        this.dates = dates;
    }

    public Chart(float[] lowValues, String[] dates) {
        this.lowValues = lowValues;
        this.dates = dates;
    }

    public float[] getLowValues() {
        return lowValues;
    }

    public float[] getHightValues() {
        return hightValues;
    }

    public String[] getDates() {
        return dates;
    }
}
