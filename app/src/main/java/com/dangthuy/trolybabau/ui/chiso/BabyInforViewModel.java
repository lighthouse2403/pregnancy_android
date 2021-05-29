package com.dangthuy.trolybabau.ui.chiso;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.data.model.BabyInfo;
import com.dangthuy.trolybabau.data.model.Chart;
import com.dangthuy.trolybabau.data.repository.PregnancyRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.dangthuy.trolybabau.MainActivity.appDatabase;

/**
 * Created by nhongthai on 21/03/2021.
 */
public class BabyInforViewModel extends BaseViewModel {
    private final MutableLiveData<List<BabyInfo>> babyInfos = new MutableLiveData<>();
    private final MutableLiveData<List<String>> images = new MutableLiveData<>();
    private final MutableLiveData<float[]> liveValue = new MutableLiveData<>();
    private final MutableLiveData<Chart> liveChart = new MutableLiveData<>();
    private List<String> imageList;
    private int year, month, dayOfMonth, hour, min;
    private Map<String, List<BabyIndex>> weekMap;
    private float[] lowValues, hightValues;
    private String[] dates;

    private PregnancyRepository.LoadBabyIndexListener babyIndexListener = response -> {
        if (response != null) {
            if (response.getDetails() != null) {
                List<BabyIndex> details = response.getDetails();
                lowValues = new float[365];
                hightValues = new float[365];
                dates = new String[365];
                int oldDay = 0;
                int yearBegin = sharedPrefs.get(Constants.YEAR_BEGIN, Integer.class);
                int monthBegin = sharedPrefs.get(Constants.MONTH_BEGIN, Integer.class);
                int dayBegin = sharedPrefs.get(Constants.DAY_BEGIN, Integer.class);
                Calendar calendar = Calendar.getInstance();
                calendar.set(yearBegin, monthBegin, dayBegin);
                for (int i = 0; i < 365; i++) {
                    dates[i] = (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                for (BabyIndex detail : details) {
                    String[] week = detail.getWeek().split("\\+");
                    int day = Integer.parseInt(week[0]) * 7 + Integer.parseInt(week[1]);
                    String[] split = detail.getEfwGh().split("-");
                    if (day - oldDay == 1) {
                        lowValues[day] = Float.parseFloat(split[0]);
                        hightValues[day] = Float.parseFloat(split[1]);
                    } else {
                        lowValues[day] = lowValues[oldDay];
                        hightValues[day] = hightValues[oldDay];
                    }
                    oldDay = day;
                }
                Chart chart = new Chart(lowValues, hightValues, dates);
                liveChart.postValue(chart);
            }
        }
    };

    public BabyInforViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchDataListBabyInfo() {
        new Thread(() -> babyInfos.postValue(appDatabase.babyInfoDao().findAll())).start();
    }

    public MutableLiveData<List<BabyInfo>> getBabyInfos() {
        return babyInfos;
    }

    public void setDate(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = day;
        this.hour = hour;
        this.min = minute;
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
        return DateUtils.getDate(year, month, dayOfMonth);
    }

    public void fetchImages(Intent data) {
        imageList = new ArrayList<>();
        if (data != null) {
            if (data.getData() != null) {
                Uri uri = data.getData();
                imageList.add(uri.toString());
            } else if (data.getClipData() != null) {
                for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    imageList.add(uri.toString());
                }
            }
            images.postValue(imageList);
        }
    }

    public MutableLiveData<List<String>> getImages() {
        return images;
    }

    public void saveBabyInfoToDb(String weight) {
        String image = "";
        if (imageList != null && imageList.size() > 0) {
            image = imageList.get(0);
        }
        //caculate from the begin to the time save
        Calendar calendar = Calendar.getInstance();
        int yearBegin = sharedPrefs.get(Constants.YEAR_BEGIN, Integer.class);
        int monthBegin = sharedPrefs.get(Constants.MONTH_BEGIN, Integer.class);
        int dayBegin = sharedPrefs.get(Constants.DAY_BEGIN, Integer.class);
        calendar.set(yearBegin, monthBegin, dayBegin);
        Date date = calendar.getTime();
        calendar.set(year, month, dayOfMonth);
        Date now = calendar.getTime();
        long diff = now.getTime() - date.getTime();
        int totalDay = (int) (diff / 1000 / 60 / 60 / 24);
        int week = totalDay / 7;
        int day = totalDay % 7;
        BabyInfo babyInfo = new BabyInfo(year, month, dayOfMonth, weight, image, week, day);
        new Thread(() -> appDatabase.babyInfoDao().insert(babyInfo)).start();
    }

    public void fetchChart() {
        PregnancyRepository repository = new PregnancyRepository(mContext);
        repository.loadBabyIndex(R.raw.baby_index_detail, babyIndexListener);
    }

    public MutableLiveData<float[]> getLiveValue() {
        return liveValue;
    }

    public MutableLiveData<Chart> getLiveChart() {
        return liveChart;
    }
}
