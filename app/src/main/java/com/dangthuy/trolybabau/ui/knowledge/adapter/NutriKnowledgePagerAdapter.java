package com.dangthuy.trolybabau.ui.knowledge.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dangthuy.trolybabau.ui.knowledge.nutri.NutriFragment;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class NutriKnowledgePagerAdapter extends FragmentStatePagerAdapter {
    public NutriKnowledgePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NutriFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
