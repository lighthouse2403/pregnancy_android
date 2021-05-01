package com.dangthuy.trolybabau.ui.clothes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.ClothesSection;
import com.dangthuy.trolybabau.data.repository.ClothesRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class ClothesViewModel extends BaseViewModel {
    private final MutableLiveData<List<ClothesSection>> sections = new MutableLiveData<>();
    private final ClothesRepository.LoadClothesHospitalListener clothesHospitalListener = response -> {
        if (response != null) {
            List<ClothesSection> sectionList = new ArrayList<>();
            if (response.getWinterCloset() != null) {
                sectionList.add(new ClothesSection(mContext.getResources().getString(R.string.tv_quan_ao_mua_dong_cho_con), response.getWinterCloset()));
            }
            if (response.getSummerCloset() != null) {
                sectionList.add(new ClothesSection(mContext.getResources().getString(R.string.tv_quan_ao_mua_he_cho_con), response.getSummerCloset()));
            }
            if (response.getMamaCloset() != null) {
                sectionList.add(new ClothesSection(mContext.getResources().getString(R.string.tv_quan_ao_sau_sinh_cho_me), response.getMamaCloset()));
            }
            if (response.getDiaper() != null) {
                sectionList.add(new ClothesSection(mContext.getResources().getString(R.string.tv_ta_va_bim), response.getDiaper()));
            }
            if (response.getMilkBottle() != null) {
                sectionList.add(new ClothesSection(mContext.getResources().getString(R.string.tv_binhsua_va_cac_dung_cu_khac), response.getMilkBottle()));
            }
            if (response.getDrug() != null) {
                sectionList.add(new ClothesSection(mContext.getResources().getString(R.string.tv_dung_cu_y_te), response.getDrug()));
            }
            sections.postValue(sectionList);
        }
    };

    public ClothesViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        new ClothesRepository(mContext).loadClothesHospital(R.raw.hospital_list, clothesHospitalListener);
    }

    public MutableLiveData<List<ClothesSection>> getSections() {
        return sections;
    }
}
