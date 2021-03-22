package com.dangthuy.trolybabau.ui.share_corner.mine;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class MyShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    public static final String TAG = "MyShareCornerFragment";

    private FragmentPagerCommonBinding binding;

    public static MyShareCornerFragment newInstance() {
        MyShareCornerFragment fragment = new MyShareCornerFragment();
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
        binding = (FragmentPagerCommonBinding) getBinding();
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }
}
