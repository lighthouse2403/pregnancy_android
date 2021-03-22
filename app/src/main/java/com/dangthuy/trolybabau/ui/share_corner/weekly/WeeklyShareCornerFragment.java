package com.dangthuy.trolybabau.ui.share_corner.weekly;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class WeeklyShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    public static final String TAG = "WeeklyShareCornerFragme";

    private FragmentPagerCommonBinding binding;

    public static WeeklyShareCornerFragment newInstance() {
        WeeklyShareCornerFragment fragment = new WeeklyShareCornerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ShareCornerViewModel> provideViewModelClass() {
        return ShareCornerViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager_common;
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
