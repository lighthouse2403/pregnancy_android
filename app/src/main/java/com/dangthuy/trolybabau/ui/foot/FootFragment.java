package com.dangthuy.trolybabau.ui.foot;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentInfomationCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;

/**
 * Created by nhongthai on 3/31/2021.
 */
public class FootFragment extends BaseFragment<FootViewModel> {
    public static final String TAG = "FootFragment";
    private FragmentInfomationCommonBinding binding;

    public static FootFragment newInstance() {
        FootFragment fragment = new FootFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<FootViewModel> provideViewModelClass() {
        return FootViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_infomation_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentInfomationCommonBinding) getBinding();
        if (getArguments() != null) {

        }
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }
}
