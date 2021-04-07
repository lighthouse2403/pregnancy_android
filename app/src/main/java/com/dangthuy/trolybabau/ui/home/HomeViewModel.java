package com.dangthuy.trolybabau.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.data.repository.HomeRepository;
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
    public static final String TEN_HAY_CHO_BE = "Tên hay cho bé";
    public static final String BAC_SI = "Bác sĩ";
    public static final String DO_SO_SINH = "Đồ sơ sinh";
    public static final String DIA_CHI_TIEM_PHONG = "Địa chỉ tiêm phòng uy tín";
    public static final String MON_NGON_MOI_NGAY = "Món ngon mỗi ngày";
    public static final String NHAC_NHO = "Nhắc nhở";
    private final MutableLiveData<List<HomeMenu>> homeMenus = new MutableLiveData<>();
    private final HomeRepository.LoadHomeListener babyNameListener = response -> {
        if (response != null) {

        }
    };

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(GOC_CHIA_SE, AppCompatResources.getDrawable(mContext, R.drawable.chia_se)));
        list.add(new HomeMenu(THEO_DOI_SO_LAN_DAP, AppCompatResources.getDrawable(mContext, R.drawable.foot)));
        list.add(new HomeMenu(CAN_NANG_CUA_ME, AppCompatResources.getDrawable(mContext, R.drawable.baby_name)));
        list.add(new HomeMenu(CAU_CHUYEN_SINH_NO, AppCompatResources.getDrawable(mContext, R.drawable.baby_name)));
        list.add(new HomeMenu(NHAC_BAU_CHO_BE, AppCompatResources.getDrawable(mContext, R.drawable.playlist)));
        list.add(new HomeMenu(BAC_SI, AppCompatResources.getDrawable(mContext, R.drawable.doctor_list)));
        list.add(new HomeMenu(DO_SO_SINH, AppCompatResources.getDrawable(mContext, R.drawable.newbornthings)));
        list.add(new HomeMenu(DIA_CHI_TIEM_PHONG, AppCompatResources.getDrawable(mContext, R.drawable.baby_name)));
        list.add(new HomeMenu(MON_NGON_MOI_NGAY, AppCompatResources.getDrawable(mContext, R.drawable.mon_an)));
        list.add(new HomeMenu(NHAC_NHO, AppCompatResources.getDrawable(mContext, R.drawable.calendar)));
        fetchBabyName();
        homeMenus.postValue(list);
    }

    public MutableLiveData<List<HomeMenu>> getHomeMenus() {
        return homeMenus;
    }

    public void fetchBabyName() {
        new HomeRepository(mContext).loadBabyName(babyNameListener, R.raw.babyname);
    }
}
