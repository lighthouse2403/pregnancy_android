package com.dangthuy.trolybabau.ui.baby_index;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.data.repository.PregnancyRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class BabyIndexViewModel extends BaseViewModel {
    private final MutableLiveData<List<BabyIndex>> indexLiveData = new MutableLiveData<>();

    private List<BabyIndex> babyIndexList, mData;

    private final PregnancyRepository.LoadBabyIndexListener babyIndexListener = response -> {
        if (response != null) {
            if(response.getDetails() != null) {
                mData = response.getDetails();
                //filter follow week
                babyIndexList = new ArrayList<>();
                for (BabyIndex babyIndex : response.getDetails()) {
                    if (babyIndex.getWeek().endsWith("0")) {
                        babyIndexList.add(babyIndex);
                    }
                }
                Log.d("thainh","???");
                indexLiveData.postValue(babyIndexList);
            }
        }
    };

    public BabyIndexViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        new PregnancyRepository(mContext).loadBabyIndex(R.raw.baby_index_detail, babyIndexListener);
    }

    public MutableLiveData<List<BabyIndex>> getIndexLiveData() {
        return indexLiveData;
    }

    public List<BabyIndex> getData(int week) {
        List<BabyIndex> list = new ArrayList<>();
        for (BabyIndex babyIndex : mData) {
            if (babyIndex.getWeek().startsWith(String.valueOf(week))) {
                list.add(babyIndex);
            }
        }
        return list;
    }
}
