package com.dangthuy.trolybabau.ui.share_corner.hot;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;
import com.dangthuy.trolybabau.ui.share_corner.all.AllShareCornerFragment;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class HotShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    private static final String TAG = "HotShareCornerFragment";
    private FragmentPagerCommonBinding binding;

    public static HotShareCornerFragment newInstance() {
        HotShareCornerFragment fragment = new HotShareCornerFragment();
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
