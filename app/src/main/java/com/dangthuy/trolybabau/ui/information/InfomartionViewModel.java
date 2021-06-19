package com.dangthuy.trolybabau.ui.information;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.dao.BabyFootDao;
import com.dangthuy.trolybabau.data.model.BabyFoot;
import com.dangthuy.trolybabau.data.model.Chart;
import com.dangthuy.trolybabau.data.model.MomWeight;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.dangthuy.trolybabau.MainActivity.appDatabase;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class InfomartionViewModel extends BaseViewModel {
    private final MutableLiveData<List<BabyFoot>> liveBabyFoot = new MutableLiveData<>();
    private final MutableLiveData<List<MomWeight>> liveMomWeight = new MutableLiveData<>();
    private final MutableLiveData<Chart> liveChart = new MutableLiveData<>();
    private int mCount;
    private int mHour = 0, mMin = 0, mSecond = 0, mDay = 0, mMonth = 0, mYear = 0, mHourFoot = 0, mMinFoot = 0, mSecondFoot = 0;
    private List<MomWeight> momWeights;
    private List<BabyFoot> babyFoots;
    private float[] values;
    private String[] dates;
    private List<Integer> dayVal;
    private Date mDate;

    public InfomartionViewModel(@NonNull Application application) {
        super(application);
    }

    public static final int TYPE_MOM = 0;
    public static final int TYPE_BABY = 1;
    private int mType;

    public void setDate(int year, int month, int day, int hour, int min) {
        this.mYear = year;
        this.mMonth = month;
        this.mDay = day;
        this.mHour = hour;
        this.mMin = min;
        this.mSecond = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDay, mHour, mMin, mSecond);
        this.mDate = calendar.getTime();
        calculateWeek(mDate);
    }

    public void setDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.mYear = calendar.get(Calendar.YEAR);
        this.mMonth = calendar.get(Calendar.MONTH);
        this.mDay = calendar.get(Calendar.DAY_OF_MONTH);
        this.mHour = calendar.get(Calendar.HOUR_OF_DAY);
        this.mMin = calendar.get(Calendar.MINUTE);
        this.mSecond = 0;
        calendar.set(mYear, mMonth, mDay, mHour, mMin, mSecond);
        this.mDate = calendar.getTime();
        calculateWeek(mDate);
    }

    public Date getmDate() {
        return mDate;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public int getmType() {
        return mType;
    }

    public int getCount() {
        return this.mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public void setmMin(int mMin) {
        this.mMin = mMin;
    }

    public void setmSecond(int mSecond) {
        this.mSecond = mSecond;
    }

    public int getmHour() {
        return mHour;
    }

    public int getmMin() {
        return mMin;
    }

    public int getmSecond() {
        return mSecond;
    }

    public void setmHourFoot(int mHourFoot) {
        this.mHourFoot = mHourFoot;
    }

    public void setmMinFoot(int mMinFoot) {
        this.mMinFoot = mMinFoot;
    }

    public void setmSecondFoot(int mSecondFoot) {
        this.mSecondFoot = mSecondFoot;
    }

    public int getmHourFoot() {
        return mHourFoot;
    }

    public int getmMinFoot() {
        return mMinFoot;
    }

    public int getmSecondFoot() {
        return mSecondFoot;
    }

    public void saveToDb() {
        BabyFootDao babyFootDao = appDatabase.babyFootDao();
        BabyFoot babyFoot = new BabyFoot(mCount, DateUtils.getText(mYear, mMonth, mDay, mHour, mMin), mHourFoot, mMinFoot, mSecondFoot);
        new Thread(() -> babyFootDao.insert(babyFoot)).start();
    }

    public void fetchBabyFootDb() {
        new Thread(() -> {
            babyFoots = appDatabase.babyFootDao().findAll();
            liveBabyFoot.postValue(babyFoots);
            fetchChart();
        }).start();
    }

    public void fetchMomWeightDb() {
        new Thread(() -> {
            momWeights = appDatabase.momWeightDao().findAll();
            liveMomWeight.postValue(momWeights);
            fetchChart();
        }).start();
    }

    public MutableLiveData<List<BabyFoot>> getLiveBabyFoot() {
        return liveBabyFoot;
    }

    public MutableLiveData<List<MomWeight>> getLiveMomWeight() {
        return liveMomWeight;
    }

    public void saveMomWeightToDb(String weight) {
        MomWeight momWeight = new MomWeight(weight, getTime(), getWeek(), getDay());
        new Thread(() -> appDatabase.momWeightDao().insert(momWeight)).start();
    }

    private String getTime() {
        return DateUtils.getText(mContext, mYear, mMonth, mDay, mHour, mMin) + ":00";
    }

    public void fetchChart() {
        values = new float[365];
        dayVal = new ArrayList<>();
        if (mType == TYPE_MOM) {
            for (MomWeight momWeight : momWeights) {
                int day = momWeight.week * 7 + momWeight.dayOfWeek;
                values[day] = Float.parseFloat(momWeight.weight);
                dayVal.add(day);
            }
        } else {
            for (BabyFoot babyFoot : babyFoots) {
                Date date = DateUtils.parseDate(babyFoot.createdDate);
                if (date != null) {
                    calculateWeek(date);
                    int day = getWeek() * 7 + getDay();
                    values[day] = babyFoot.foot;
                    dayVal.add(day);
                }
            }
        }
        Collections.sort(dayVal);
        for (int i = 0; i < dayVal.size() - 1; i++) {
            int diff = dayVal.get(i + 1) - dayVal.get(i);
            for (int j = dayVal.get(i) + 1; j < dayVal.get(i + 1); j++) {
                values[j] = values[dayVal.get(i)] + ((values[dayVal.get(i + 1)] - values[dayVal.get(i)]) * (j - dayVal.get(i)) / diff);
            }
        }
        dates = new String[365];
        int yearBegin = sharedPrefs.get(Constants.YEAR_BEGIN, Integer.class);
        int monthBegin = sharedPrefs.get(Constants.MONTH_BEGIN, Integer.class);
        int dayBegin = sharedPrefs.get(Constants.DAY_BEGIN, Integer.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(yearBegin, monthBegin, dayBegin);
        for (int i = 0; i < 365; i++) {
            dates[i] = (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        Chart chart = new Chart(values, dates);
        liveChart.postValue(chart);
    }

    public MutableLiveData<Chart> getLiveChart() {
        return liveChart;
    }
}
