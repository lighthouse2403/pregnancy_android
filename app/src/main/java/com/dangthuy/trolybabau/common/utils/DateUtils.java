package com.dangthuy.trolybabau.common.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DateUtils {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatDate(Date date) {
        String[] split = format.format(date).split("/");
        return split[0] + " tháng " + split[1] + "," + split[2];
    }
}
