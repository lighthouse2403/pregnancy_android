package com.dangthuy.trolybabau.ui.thaiky.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.data.model.Pregnancy;
import com.dangthuy.trolybabau.ui.thaiky.detail.DetailThaiKyFragment;

import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class ThaiKyPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Pregnancy> pregnancyList;
    private final List<BabyIndex> babyIndexs;

    public ThaiKyPagerAdapter(@NonNull FragmentManager fm, List<Pregnancy> pregnancies, List<BabyIndex> babyIndexs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.pregnancyList = pregnancies;
        this.babyIndexs = babyIndexs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DetailThaiKyFragment.newInstance(position, this.pregnancyList.get(position), this.babyIndexs.get(position));
    }

    @Override
    public int getCount() {
        return this.pregnancyList.size();
    }
}
