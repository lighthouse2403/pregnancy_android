package com.dangthuy.trolybabau.ui.thaiky;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentThaikyBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.main.MainFragment;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ThaikyFragment extends BaseFragment<ThaikyViewModel> {
    private static final String TAG = "ThaikyFragment";
    private FragmentThaikyBinding binding;

    public static ThaikyFragment newInstance() {
        ThaikyFragment fragment = new ThaikyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ThaikyViewModel> provideViewModelClass() {
        return ThaikyViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_thaiky;
    }

    @Override
    protected void initView() {
        binding = (FragmentThaikyBinding) getBinding();
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }
}
