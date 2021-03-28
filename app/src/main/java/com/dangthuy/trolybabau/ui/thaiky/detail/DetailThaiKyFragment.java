package com.dangthuy.trolybabau.ui.thaiky.detail;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentThaikyDetailBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.thaiky.ThaikyFragment;
import com.dangthuy.trolybabau.ui.thaiky.ThaikyViewModel;
import com.dangthuy.trolybabau.ui.thaiky.adapter.ThaiKyPagerAdapter;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DetailThaiKyFragment extends BaseFragment<ThaikyViewModel> {
    public static final String TAG = "DetailThaiKyFragment";
    private FragmentThaikyDetailBinding binding;

    public static DetailThaiKyFragment newInstance(int position) {
        DetailThaiKyFragment fragment = new DetailThaiKyFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ThaikyViewModel> provideViewModelClass() {
        return ThaikyViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_thaiky_detail;
    }

    @Override
    protected void initView() {
        binding = (FragmentThaikyDetailBinding) getBinding();
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
