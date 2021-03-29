package com.dangthuy.trolybabau.ui.chiso.chart;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentChartBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInforViewModel;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ChartBabyInfoFragment extends BaseFragment<BabyInforViewModel> {
    public static final String TAG = "ChartBabyInfoFragment";

    private FragmentChartBinding binding;

    public static ChartBabyInfoFragment newInstance() {
        ChartBabyInfoFragment fragment = new ChartBabyInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyInforViewModel> provideViewModelClass() {
        return BabyInforViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initView() {
        binding = (FragmentChartBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
    }

    private void setLayoutView() {

    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }
}
