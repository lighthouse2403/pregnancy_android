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

    private static final SimpleDateFormat formatDb = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String formatDate(Date date) {
        String[] split = format.format(date).split("/");
        return split[0] + " tháng " + split[1] + "," + split[2];
    }

    public static String formatDateDb(Date date) {
        return formatDb.format(date);
    }

    public static String getDate(int year, int month, int day) {
        return day + " tháng " + (month + 1) + ", " + year;
    }
}
