package com.dangthuy.trolybabau.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.dangthuy.trolybabau.R;

import java.text.ParseException;
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

    public static Date parseDate(String dstr) {
        try {
            Date date = formatDb.parse(dstr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDate(int year, int month, int day) {
        return day + " tháng " + (month + 1) + ", " + year;
    }

    public static String getText(Context context, int year, int month, int day, int hour, int min) {
        return day + " " + context.getString(R.string.tv_thang) + " " + (month + 1) + ", " + year + " " + (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);
    }

    public static String getText(int year, int month, int day, int hour, int min) {
        return (day < 10 ? "0" + day : day) + "/" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/" + year + " " + (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) + ":00";
    }

    public static String getAge(int week, int day) {
        return week + " tuần " + day + " ngày";
    }
}
