package com.dangthuy.trolybabau.ui.alarm;

import android.app.Application;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.Alarm;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.dangthuy.trolybabau.MainActivity.appDatabase;

/**
 * Created by nhongthai on 4/26/2021.
 */
public class AlarmViewModel extends BaseViewModel {
    private final MutableLiveData<List<Alarm>> alarms = new MutableLiveData<>();
    private Alarm mAlarm;
    private boolean isAdd;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        this.isAdd = true;
    }

    public void fetchData() {
        new Thread(() -> alarms.postValue(appDatabase.alarmDao().findAll())).start();
    }

    public MutableLiveData<List<Alarm>> getAlarms() {
        return alarms;
    }

    public void setAlarm(Alarm alarm) {
        if (alarm == null) {
            Calendar calendar = Calendar.getInstance();
            this.mAlarm = new Alarm(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    mContext.getResources().getString(R.string.tv_nhac_nho),
                    true
                    );
            this.isAdd = true;
        } else {
            this.mAlarm = alarm;
            this.isAdd = false;
        }
    }

    public void setAlarm(int year, int month, int dayOfMonth, int hour, int min) {
        mAlarm.setYear(year);
        mAlarm.setMonth(month);
        mAlarm.setDayOfMonth(dayOfMonth);
        mAlarm.setHour(hour);
        mAlarm.setMin(min);
    }

    public Alarm getAlarm() {
        return mAlarm;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void saveAlarm(String note, boolean isOn) {
        mAlarm.setNote(note);
        mAlarm.setStatus(isOn);
        new Thread(() -> {
            if (isAdd) {
                appDatabase.alarmDao().insert(mAlarm);
            } else {
                int result = appDatabase.alarmDao().update(mAlarm);
                Log.d("thainh", "result " + result);
            }
        }).start();
    }

    public String getDate() {
        if (isAdd)
            return DateUtils.formatDate(new Date());
        return mAlarm.getDayOfMonth() + " " + mContext.getResources().getString(R.string.tv_ngay) + " " + mAlarm.getMonth() + ", " + mAlarm.getYear();
    }

    public String getTime() {
        if (isAdd) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);
        }
        return (mAlarm.getHour() < 10 ? "0" + mAlarm.getHour() : mAlarm.getHour()) + ":" + (mAlarm.getMin() < 10 ? "0" + mAlarm.getMin() : mAlarm.getMin());
    }

    public boolean isCheck() {
        if (isAdd)
            return false;
        return mAlarm.isStatus();
    }

    public String getNote() {
        if (isAdd)
            return mContext.getResources().getString(R.string.tv_nhac_nho);
        return mAlarm.getNote();
    }

    public long getSchedule() {
        Calendar calendar = Calendar.getInstance();
//        long now = calendar.getTime().getTime();
        calendar.set(Calendar.YEAR, mAlarm.getYear());
        calendar.set(Calendar.MONTH, mAlarm.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, mAlarm.getDayOfMonth());
        calendar.set(Calendar.HOUR, mAlarm.getHour());
        calendar.set(Calendar.MINUTE, mAlarm.getMin());
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public void switchOnOff(Alarm item) {
        this.isAdd = false;
        item.setStatus(!item.isStatus());
        this.mAlarm = item;
        saveAlarm(mAlarm.getNote(), mAlarm.isStatus());
    }
}
