package com.dangthuy.trolybabau.ui.information.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.ui.information.chart.ChartInformationFragment;
import com.dangthuy.trolybabau.ui.information.list.ListInformationFragment;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class InformationPagerAdapter extends FragmentStatePagerAdapter {
    private final int mType;
    public InformationPagerAdapter(@NonNull FragmentManager fm, int type) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mType = type;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return ListInformationFragment.newInstance(mType);
        else
            return ChartInformationFragment.newInstance();
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
