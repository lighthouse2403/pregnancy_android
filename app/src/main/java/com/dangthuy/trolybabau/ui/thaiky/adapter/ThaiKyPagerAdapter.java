package com.dangthuy.trolybabau.ui.thaiky.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.ui.thaiky.detail.DetailThaiKyFragment;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class ThaiKyPagerAdapter extends FragmentStatePagerAdapter {
    public ThaiKyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DetailThaiKyFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 50;
    }
}
