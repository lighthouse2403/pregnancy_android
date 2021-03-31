package com.dangthuy.trolybabau.ui.mom_weight.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.ui.mom_weight.chart.ChartMomWeightFragment;
import com.dangthuy.trolybabau.ui.mom_weight.list.ListMomWeightFragment;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class InformationPagerAdapter extends FragmentStatePagerAdapter {
    public InformationPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return ListMomWeightFragment.newInstance();
        else
            return ChartMomWeightFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
