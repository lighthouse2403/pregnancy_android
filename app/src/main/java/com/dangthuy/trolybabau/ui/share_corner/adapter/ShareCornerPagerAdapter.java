package com.dangthuy.trolybabau.ui.share_corner.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.listener.ILoadListener;
import com.dangthuy.trolybabau.ui.home.HomeFragment;
import com.dangthuy.trolybabau.ui.share_corner.all.AllShareCornerFragment;
import com.dangthuy.trolybabau.ui.share_corner.mine.MyShareCornerFragment;
import com.dangthuy.trolybabau.ui.thaiky.ThaikyFragment;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareCornerPagerAdapter extends FragmentStatePagerAdapter {
    private ILoadListener<Share> loadListener;

    public void setLoadListener(ILoadListener<Share> loadListener) {
        this.loadListener = loadListener;
    }

    public ShareCornerPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllShareCornerFragment fragment = AllShareCornerFragment.newInstance();
                fragment.setLoadListener(shares -> loadListener.onLoaded(shares));
                return fragment;
            case 1:
                return MyShareCornerFragment.newInstance();
            case 2:
                return MyShareCornerFragment.newInstance();
            case 3:
                return MyShareCornerFragment.newInstance();
            case 4:
                return MyShareCornerFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
