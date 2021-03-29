package com.dangthuy.trolybabau.ui.chiso.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.ui.chiso.chart.ChartBabyInfoFragment;
import com.dangthuy.trolybabau.ui.chiso.list.ListBabyInfoFragment;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class BabyInfoPagerAdapter extends FragmentStatePagerAdapter {
    public BabyInfoPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return ListBabyInfoFragment.newInstance();
        } else
            return ChartBabyInfoFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
