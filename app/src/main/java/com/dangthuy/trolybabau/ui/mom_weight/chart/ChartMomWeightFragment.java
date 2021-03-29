package com.dangthuy.trolybabau.ui.mom_weight.chart;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.mom_weight.MomWeightFragment;
import com.dangthuy.trolybabau.ui.mom_weight.MomWeightViewModel;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ChartMomWeightFragment extends BaseFragment<MomWeightViewModel> {
    public static final String TAG = "ChartMomWeightFragment";

    public static MomWeightFragment newInstance() {
        MomWeightFragment fragment = new MomWeightFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<MomWeightViewModel> provideViewModelClass() {
        return MomWeightViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
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
