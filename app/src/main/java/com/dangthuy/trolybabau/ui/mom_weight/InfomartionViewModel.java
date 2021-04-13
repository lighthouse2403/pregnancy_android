package com.dangthuy.trolybabau.ui.mom_weight;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.dao.BabyFootDao;
import com.dangthuy.trolybabau.data.model.BabyFoot;
import com.dangthuy.trolybabau.data.model.MomWeight;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.Date;
import java.util.List;

import static com.dangthuy.trolybabau.MainActivity.appDatabase;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class InfomartionViewModel extends BaseViewModel {
    private final MutableLiveData<List<BabyFoot>> babyFoots = new MutableLiveData<>();
    private final MutableLiveData<List<MomWeight>> momWeights = new MutableLiveData<>();
    private int mCount;
    private int mHour = 0, mMin = 0, mSecond = 0, mDay = 0, mMonth = 0, mYear = 0;

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

    public void saveToDb() {
        BabyFootDao babyFootDao = appDatabase.babyFootDao();
        BabyFoot babyFoot = new BabyFoot(mCount, DateUtils.formatDateDb(new Date()), mHour, mMin, mSecond);
        new Thread(() -> babyFootDao.insert(babyFoot)).start();
    }

    public void fetchBabyFootDb() {
        new Thread(() -> babyFoots.postValue(appDatabase.babyFootDao().findAll())).start();
    }

    public void fetchMomWeightDb() {
        new Thread(() -> momWeights.postValue(appDatabase.momWeightDao().findAll())).start();
    }

    public MutableLiveData<List<BabyFoot>> getBabyFoots() {
        return babyFoots;
    }

    public MutableLiveData<List<MomWeight>> getMomWeights() {
        return momWeights;
    }

    public void saveMomWeightToDb(String weight) {
        MomWeight momWeight = new MomWeight(weight, getTime(), 32, 2);
        new Thread(() -> appDatabase.momWeightDao().insert(momWeight)).start();
    }

    private String getTime() {
        return mDay + "/" + mMonth + "/" + mYear + " " + mHour + ":" + mMin + "00";
    }
}
