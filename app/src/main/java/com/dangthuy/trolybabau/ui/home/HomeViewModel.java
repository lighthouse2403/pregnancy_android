package com.dangthuy.trolybabau.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeViewModel extends BaseViewModel {
    private MutableLiveData<List<HomeMenu>> homeMenus = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(HomeMenu.GOC_CHIA_SE, ""));
        list.add(new HomeMenu(HomeMenu.NHAC_CHO_THAI_NHI, ""));
        homeMenus.postValue(list);
    }

    public MutableLiveData<List<HomeMenu>> getHomeMenus() {
        return homeMenus;
    }
}
