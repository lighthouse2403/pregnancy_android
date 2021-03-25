package com.dangthuy.trolybabau.ui.born_story;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.HomeFragment;

/**
 * Created by nhongthai on 3/24/2021.
 */
public class BornStoryFragment extends BaseFragment<BornStoryViewModel> {
    public static final String TAG = "BornStoryFragment";

    public static BornStoryFragment newInstance() {
        BornStoryFragment fragment = new BornStoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BornStoryViewModel> provideViewModelClass() {
        return BornStoryViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }
}
