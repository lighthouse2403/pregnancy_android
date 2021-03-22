package com.dangthuy.trolybabau.ui.share_corner.like;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;
import com.dangthuy.trolybabau.ui.share_corner.mine.MyShareCornerFragment;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class LikeShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    private static final String TAG = "LikeShareCornerFragment";

    private FragmentPagerCommonBinding binding;

    public static LikeShareCornerFragment newInstance() {
        LikeShareCornerFragment fragment = new LikeShareCornerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected Class<ShareCornerViewModel> provideViewModelClass() {
        return null;
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
