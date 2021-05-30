package com.dangthuy.trolybabau.ui.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.common.sharePrefs.SharedPrefsImpl;
import com.dangthuy.trolybabau.common.utils.Constants;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nhongthai on 8/3/2020.
 */
public abstract class BaseViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    protected Context mContext;

    protected SharedPrefsImpl sharedPrefs;

    private int week, day;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mContext = getApplication().getApplicationContext();
        this.sharedPrefs = SharedPrefsImpl.newInstance(Constants.CACHE, mContext);
    }

    protected final MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLiveData() {
        return liveData;
    }

    protected final MutableLiveData<String> liveToast = new MutableLiveData<>();

    public MutableLiveData<String> getLiveToast() {
        return liveToast;
    }

    public void calculateWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, sharedPrefs.get(Constants.YEAR_BEGIN, Integer.class));
        calendar.set(Calendar.MONTH, sharedPrefs.get(Constants.MONTH_BEGIN, Integer.class));
        calendar.set(Calendar.DAY_OF_MONTH, sharedPrefs.get(Constants.DAY_BEGIN, Integer.class));
        Date beginDate = calendar.getTime();
        long duration = ((new Date()).getTime() - beginDate.getTime()) / 1000 / 60 / 60 / 24;
        this.week = (int) (duration / 7);
        this.day = (int) (duration % 7);
    }

    public int getWeek() {
        return week;
    }

    public int getDay() {
        return day;
    }
}
