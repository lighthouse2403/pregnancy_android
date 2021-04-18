package com.dangthuy.trolybabau.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ProfileViewModel extends BaseViewModel {
    private int week, day, year, month, dayExpect, beginYear, beginMonth, beginDay;
    private boolean isSetup;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveData() {
        sharedPrefs.put(Constants.SET_UP, true);
        sharedPrefs.put(Constants.YEAR_BORN, year);
        sharedPrefs.put(Constants.MONTH_BORN, month);
        sharedPrefs.put(Constants.DAY_BORN, dayExpect);
        sharedPrefs.put(Constants.WEEK_AGE, week);
        sharedPrefs.put(Constants.DAY_AGE, day);
        sharedPrefs.put(Constants.YEAR_BEGIN, beginYear);
        sharedPrefs.put(Constants.MONTH_BEGIN, beginMonth);
        sharedPrefs.put(Constants.DAY_BEGIN, beginDay);
    }

    public void setAge(int week, int day) {
        this.week = week;
        this.day = day;
        calculateByAge();
    }

    public void setExpect(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.dayExpect = day;
        calculateByExpect();
    }

    public void setBegin(int year, int month, int day) {
        this.beginYear = year;
        this.beginMonth = month;
        this.beginDay = day;
        calculateByBegin();
    }

    private void calculateByBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, beginYear);
        calendar.set(Calendar.MONTH, beginMonth);
        calendar.set(Calendar.DAY_OF_MONTH, beginDay);
        Date beginDate = calendar.getTime();
        long duration = ((new Date()).getTime() - beginDate.getTime()) / 1000 / 60 / 60 / 24;
        this.week = (int) (duration / 7);
        this.day = (int) (duration % 7);
        calendar.add(Calendar.DAY_OF_MONTH, 280);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.dayExpect = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void calculateByAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -(week * 7 + day));
        this.beginYear = calendar.get(Calendar.YEAR);
        this.beginMonth = calendar.get(Calendar.MONTH);
        this.beginDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_MONTH, 280);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.dayExpect = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void calculateByExpect() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayExpect);
        //280 days is total day of pregnant process in normal condition, the same as 40 weeks, 9 months 10 days
        calendar.add(Calendar.DAY_OF_MONTH, -280);
        this.beginYear = calendar.get(Calendar.YEAR);
        this.beginMonth = calendar.get(Calendar.MONTH);
        this.beginDay = calendar.get(Calendar.DAY_OF_MONTH);
        Date beginDate = calendar.getTime();
        long duration = ((new Date()).getTime() - beginDate.getTime()) / 1000 / 60 / 60 / 24;
        this.week = (int) (duration / 7);
        this.day = (int) (duration % 7);
    }

    public void setIsSetup(boolean isSetup) {
        this.isSetup = isSetup;
    }

    public boolean isSetup() {
        return isSetup;
    }

    public int getWeek() {
        return week;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayExpect() {
        return dayExpect;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public int getBeginMonth() {
        return beginMonth;
    }

    public int getBeginDay() {
        return beginDay;
    }
}
