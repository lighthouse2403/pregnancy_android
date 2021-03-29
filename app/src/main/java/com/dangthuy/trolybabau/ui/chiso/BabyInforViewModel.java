package com.dangthuy.trolybabau.ui.chiso;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.data.model.BabyInfo;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 21/03/2021.
 */
public class BabyInforViewModel extends BaseViewModel {
    private final MutableLiveData<List<BabyInfo>> babyInfos = new MutableLiveData<>();
    public BabyInforViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchDataListBabyInfo() {
        ArrayList<BabyInfo> list= new ArrayList<>();
        list.add(new BabyInfo());
        list.add(new BabyInfo());
        babyInfos.postValue(list);
    }

    public MutableLiveData<List<BabyInfo>> getBabyInfos() {
        return babyInfos;
    }
}
