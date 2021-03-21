package com.dangthuy.trolybabau.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ProfileViewModel extends BaseViewModel {
    private int week, day, year, month, dayExpect;
    private boolean isSetup;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveData() {
        sharedPrefs.put(Constants.SET_UP, true);
    }

    public void setAge(int week, int day) {
        this.week = week;
        this.day = day;
    }

    public void setExpect(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.dayExpect = day;
    }

    public void setIsSetup(boolean isSetup) {
        this.isSetup = isSetup;
    }

    public boolean isSetup() {
        return isSetup;
    }
}
