package com.dangthuy.trolybabau.ui.share_corner.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.ui.home.HomeFragment;
import com.dangthuy.trolybabau.ui.share_corner.all.AllShareCornerFragment;
import com.dangthuy.trolybabau.ui.share_corner.mine.MyShareCornerFragment;
import com.dangthuy.trolybabau.ui.thaiky.ThaikyFragment;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareCornerPagerAdapter extends FragmentStatePagerAdapter {
    public ShareCornerPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllShareCornerFragment.newInstance();
            case 1:
                return MyShareCornerFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
