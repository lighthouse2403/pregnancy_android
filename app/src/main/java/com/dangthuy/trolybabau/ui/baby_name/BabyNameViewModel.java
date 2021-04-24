package com.dangthuy.trolybabau.ui.baby_name;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyName;
import com.dangthuy.trolybabau.data.model.BabyNameDetail;
import com.dangthuy.trolybabau.data.repository.BabyNameRepository;
import com.dangthuy.trolybabau.data.repository.HomeRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameViewModel extends BaseViewModel {
    private final MutableLiveData<List<BabyName>> babyNames = new MutableLiveData<>();
    private final MutableLiveData<List<BabyNameDetail>> babyNameDetails = new MutableLiveData<>();
    private Map<String, List<BabyName>> babyNameMap;
    private List<BabyName> babyNameList;
    private final BabyNameRepository.LoadHomeListener babyNameListener = response -> {
        if (response != null && response.getBabyNames() != null) {
            babyNames.postValue(response.getBabyNames());
        }
    };
    private int mTab;
    private String firstCharacter;

    public BabyNameViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        mTab = 0;
        new BabyNameRepository(mContext).loadBabyName(babyNameListener, R.raw.babyname);
    }

    public MutableLiveData<List<BabyName>> getBabyNames() {
        return babyNames;
    }

    public void setBabyNames(List<BabyName> babyNames) {
        this.babyNameList = babyNames;
        babyNameMap = new HashMap<>();
        for (BabyName babyName : babyNameList) {
            List<BabyName> list;
            if (babyNameMap.get(babyName.getFirstCharacter()) == null) {
                list = new ArrayList<>();
            } else {
                list = babyNameMap.get(babyName.getFirstCharacter());
            }
            list.add(babyName);
            babyNameMap.put(babyName.getFirstCharacter(), list);
        }
    }

    public List<BabyName> getBabyNameList() {
        return babyNameList;
    }

    public void setTab(int tab) {
        this.mTab = tab;
    }

    public void fetchBabyName() {
        List<BabyNameDetail> listDetail = new ArrayList<>();
        if (babyNameMap != null && babyNameMap.get(firstCharacter) != null) {
            for (BabyName babyName : babyNameMap.get(firstCharacter)) {
                if (mTab == 0) {
                    String[] split = babyName.getFirstNameBoy().split(",");
                    for (String s : split) {
                        listDetail.add(new BabyNameDetail(babyName.getLastName(), s, mTab));
                    }
                } else {
                    String[] split = babyName.getFirstNameGirl().split(",");
                    for (String s : split) {
                        listDetail.add(new BabyNameDetail(babyName.getLastName(), s, mTab));
                    }
                }
            }
            babyNameDetails.postValue(listDetail);
        }
    }

    public MutableLiveData<List<BabyNameDetail>> getBabyNameDetails() {
        return babyNameDetails;
    }

    public void setFirstCharacter(String firstCharacter) {
        this.firstCharacter = firstCharacter;
    }
}
