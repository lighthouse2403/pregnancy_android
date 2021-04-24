package com.dangthuy.trolybabau.common.customview;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 * Created by nhongthai on 4/22/2021.
 */
public class MyValueFormater extends ValueFormatter {
    private String[] mValues;

    public MyValueFormater(String[] mValues) {
        this.mValues = mValues;
    }

    @Override
    public String getFormattedValue(float value) {
        return mValues[(int) value];
    }
}
