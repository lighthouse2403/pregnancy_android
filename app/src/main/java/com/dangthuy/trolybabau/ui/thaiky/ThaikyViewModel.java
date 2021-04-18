package com.dangthuy.trolybabau.ui.thaiky;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.data.model.Pregnancy;
import com.dangthuy.trolybabau.data.model.ThaiKyDetail;
import com.dangthuy.trolybabau.data.repository.PregnancyRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ThaikyViewModel extends BaseViewModel {
    private final MutableLiveData<List<Pregnancy>> pregnancies = new MutableLiveData<>();
    private final MutableLiveData<List<BabyIndex>> babyIndexs = new MutableLiveData<>();
    private final MutableLiveData<List<ThaiKyDetail>> thaikyDetails = new MutableLiveData<>();
    private final PregnancyRepository.LoadPregnancyListener pregnancyListener = response -> {
        if (response != null && response.getPregnancy() != null) {
            pregnancies.postValue(response.getPregnancy());
        }
    };
    private Pregnancy mPregnancy;
    private BabyIndex mBabyIndex;
    private List<Pregnancy> pregnancyList;
    private List<BabyIndex> babyIndexList;

    private int beginYear, beginMonth, beginDay, week;
    private Calendar calendar;

    private final PregnancyRepository.LoadBabyIndexListener babyIndexListener = response -> {
        if (response != null && response.getDetails() != null) {
            babyIndexs.postValue(response.getDetails());
        }
    };

    public ThaikyViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        PregnancyRepository repository = new PregnancyRepository(mContext);
        repository.loadPregnancy(R.raw.pregnancy_process, pregnancyListener);
        repository.loadBabyIndex(R.raw.baby_index_detail, babyIndexListener);
        this.beginYear = sharedPrefs.get(Constants.YEAR_BEGIN, Integer.class);
        this.beginMonth = sharedPrefs.get(Constants.MONTH_BEGIN, Integer.class);
        this.beginDay = sharedPrefs.get(Constants.DAY_BEGIN, Integer.class);
        this.week = sharedPrefs.get(Constants.WEEK_AGE, Integer.class);
    }

    public MutableLiveData<List<Pregnancy>> getPregnancies() {
        return pregnancies;
    }

    public void setPregnancy(Pregnancy item) {
        this.mPregnancy = item;
    }

    public Pregnancy getmPregnancy() {
        return mPregnancy;
    }

    public void setPregnancies(List<Pregnancy> pregnancies) {
        this.pregnancyList = pregnancies;
    }

    public List<Pregnancy> getPregnancyList() {
        return pregnancyList;
    }

    public List<BabyIndex> getBabyIndexList() {
        return babyIndexList;
    }

    public void setBabyIndexList(List<BabyIndex> babyIndexList) {
        this.babyIndexList = babyIndexList;
    }

    public BabyIndex getmBabyIndex() {
        return mBabyIndex;
    }

    public void setmBabyIndex(BabyIndex mBabyIndex) {
        this.mBabyIndex = mBabyIndex;
    }

    public MutableLiveData<List<BabyIndex>> getBabyIndexs() {
        return babyIndexs;
    }

    public void fetchDetail() {
        ArrayList<ThaiKyDetail> list = new ArrayList<>();
        list.add(new ThaiKyDetail("Những thay đổi của mẹ", mPregnancy.getMom()));
        list.add(new ThaiKyDetail("Những thay đổi của bé", mPregnancy.getBaby()));
        list.add(new ThaiKyDetail("Lời khuyên cho mẹ", mPregnancy.getAdvice()));
        thaikyDetails.postValue(list);
    }

    public MutableLiveData<List<ThaiKyDetail>> getThaikyDetails() {
        return thaikyDetails;
    }

    public String getDate(int week) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, beginYear);
        calendar.set(Calendar.MONTH, beginMonth);
        calendar.set(Calendar.DAY_OF_MONTH, beginDay);
        calendar.add(Calendar.WEEK_OF_MONTH, week);
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) == 0 ? "12" : calendar.get(Calendar.MONTH));
    }

    public int getWeek() {
        return week;
    }
}
