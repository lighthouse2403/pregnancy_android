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
    public static final String GOC_CHIA_SE = "Góc chia sẻ";
    public static final String CAU_CHUYEN_SINH_NO = "Câu chuyện sinh nở";
    public static final String NHAC_BAU_CHO_BE = "Nhạc bầu cho bé";
    public static final String CAN_NANG_CUA_ME = "Cân nặng mẹ bầu";
    public static final String THEO_DOI_SO_LAN_DAP = "Theo dõi số lần đạp";
    private MutableLiveData<List<HomeMenu>> homeMenus = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(GOC_CHIA_SE, ""));
        list.add(new HomeMenu(CAU_CHUYEN_SINH_NO, ""));
        list.add(new HomeMenu(NHAC_BAU_CHO_BE, ""));
        list.add(new HomeMenu(CAN_NANG_CUA_ME, ""));
        list.add(new HomeMenu(THEO_DOI_SO_LAN_DAP, ""));
        homeMenus.postValue(list);
    }

    public MutableLiveData<List<HomeMenu>> getHomeMenus() {
        return homeMenus;
    }
}
