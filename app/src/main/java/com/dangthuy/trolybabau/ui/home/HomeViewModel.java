package com.dangthuy.trolybabau.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.data.repository.HomeRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Date;
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

    private int week, day, year, month, dayExpect, remainDay, percent;
    private final MutableLiveData<List<HomeMenu>> homeMenus = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(GOC_CHIA_SE, AppCompatResources.getDrawable(mContext, R.drawable.chia_se)));
        list.add(new HomeMenu(THEO_DOI_SO_LAN_DAP, AppCompatResources.getDrawable(mContext, R.drawable.foot)));
        list.add(new HomeMenu(CAN_NANG_CUA_ME, AppCompatResources.getDrawable(mContext, R.drawable.weight)));
        list.add(new HomeMenu(CAU_CHUYEN_SINH_NO, AppCompatResources.getDrawable(mContext, R.drawable.baby_name)));
        list.add(new HomeMenu(NHAC_BAU_CHO_BE, AppCompatResources.getDrawable(mContext, R.drawable.playlist)));
        list.add(new HomeMenu(BAC_SI, AppCompatResources.getDrawable(mContext, R.drawable.doctor_list)));
        list.add(new HomeMenu(DO_SO_SINH, AppCompatResources.getDrawable(mContext, R.drawable.newbornthings)));
        list.add(new HomeMenu(TEN_HAY_CHO_BE, AppCompatResources.getDrawable(mContext, R.drawable.baby_name)));
        list.add(new HomeMenu(DIA_CHI_TIEM_PHONG, AppCompatResources.getDrawable(mContext, R.drawable.baby_name)));
        list.add(new HomeMenu(MON_NGON_MOI_NGAY, AppCompatResources.getDrawable(mContext, R.drawable.mon_an)));
        list.add(new HomeMenu(NHAC_NHO, AppCompatResources.getDrawable(mContext, R.drawable.calendar)));
//        this.week = sharedPrefs.get(Constants.WEEK_AGE, Integer.class);
//        this.day = sharedPrefs.get(Constants.DAY_AGE, Integer.class);
        calculateWeek(new Date());
        this.year = sharedPrefs.get(Constants.YEAR_BORN, Integer.class);
        this.month = sharedPrefs.get(Constants.MONTH_BORN, Integer.class);
        this.dayExpect = sharedPrefs.get(Constants.DAY_BORN, Integer.class);
        this.remainDay = 280 - getWeek() * 7 - getDay();
        this.percent = (280 - this.remainDay) * 100 / 280;
        homeMenus.postValue(list);
    }

    public MutableLiveData<List<HomeMenu>> getHomeMenus() {
        return homeMenus;
    }

//    public int getWeek() {
//        return week;
//    }
//
//    public int getDay() {
//        return day;
//    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayExpect() {
        return dayExpect;
    }

    public int getRemainDay() {
        return remainDay;
    }

    public int getPercent() {
        return percent;
    }

    public String getName() {
        String momName = sharedPrefs.get(Constants.MOM_NAME, String.class);
        String babyName = sharedPrefs.get(Constants.BABY_NAME, String.class);
        String name;
        if (momName.equals(Constants.ANDANH)) {
            momName = "";
            name = babyName;
        } else {
            name = momName + " (" + babyName + ")";
        }
        return name;
    }
}
