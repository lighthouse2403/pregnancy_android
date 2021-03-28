package com.dangthuy.trolybabau.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.ui.chiso.BabyInfoFragment;
import com.dangthuy.trolybabau.ui.diary.DiaryFragment;
import com.dangthuy.trolybabau.ui.home.HomeFragment;
import com.dangthuy.trolybabau.ui.knowledge.KnowledgePageFragment;
import com.dangthuy.trolybabau.ui.thaiky.ThaikyFragment;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return ThaikyFragment.newInstance();
            case 2:
                return DiaryFragment.newInstance();
            case 3:
                return KnowledgePageFragment.newInstance();
            case 4:
                return BabyInfoFragment.newInstance(true);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
