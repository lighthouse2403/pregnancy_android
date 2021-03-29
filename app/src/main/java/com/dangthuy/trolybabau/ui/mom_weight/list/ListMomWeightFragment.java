package com.dangthuy.trolybabau.ui.mom_weight.list;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.mom_weight.MomWeightFragment;
import com.dangthuy.trolybabau.ui.mom_weight.MomWeightViewModel;
import com.dangthuy.trolybabau.ui.mom_weight.adapter.MomWeightAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ListMomWeightFragment extends BaseFragment<MomWeightViewModel> {
    public static final String TAG = "ListMomWeightFragment";
    private FragmentPagerCommonBinding binding;
    private MomWeightAdapter mMomWeightAdapter;

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
        return R.layout.fragment_pager_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentPagerCommonBinding) getBinding();
        if (getArguments() != null) {

        }
        initAdapter();
    }

    private void initAdapter() {
        mMomWeightAdapter = new MomWeightAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5,0));
        binding.recyclerView.setAdapter(mMomWeightAdapter);
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }
}
