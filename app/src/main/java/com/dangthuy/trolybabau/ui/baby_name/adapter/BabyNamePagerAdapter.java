package com.dangthuy.trolybabau.ui.baby_name.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.data.model.BabyName;
import com.dangthuy.trolybabau.ui.baby_name.detail.DetailBabyNameFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNamePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BabyName> babyNameList;
    private String firstCharacter;
    private boolean isSpinner;

    public BabyNamePagerAdapter(@NonNull FragmentManager fm, ArrayList<BabyName> data, String firstCharacter, boolean isSpinner) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.babyNameList = data;
        this.firstCharacter = firstCharacter;
        this.isSpinner = isSpinner;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DetailBabyNameFragment.newInstance(position, this.babyNameList, firstCharacter, isSpinner);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
