package com.dangthuy.trolybabau.ui.share_corner.all;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;
import com.dangthuy.trolybabau.ui.share_corner.adapter.ShareAdapter;
import com.dangthuy.trolybabau.ui.share_corner.detail.DetailShareCornerFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class AllShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    public static final String TAG = "AllShareCornerFragment";
    private FragmentPagerCommonBinding binding;
    private ShareAdapter mShareAdapter;

    public static AllShareCornerFragment newInstance() {
        AllShareCornerFragment fragment = new AllShareCornerFragment();
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
        if (getArguments() != null) {
            setLayoutView();
        }
        initAdapter();
        viewModel.getShares().observe(this, shares -> mShareAdapter.setNewData(shares));
    }

    private void initAdapter() {
        mShareAdapter = new ShareAdapter(new ArrayList());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(4, 0));
        binding.recyclerView.setAdapter(mShareAdapter);
        mShareAdapter.setListener(item -> addFragment(R.id.container, DetailShareCornerFragment.newInstance(item),DetailShareCornerFragment.TAG, false));
    }

    private void setLayoutView() {

    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
