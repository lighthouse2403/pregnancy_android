package com.dangthuy.trolybabau.ui.share_corner.all;

import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.listener.ILoadListener;
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

    private ILoadListener<Share> loadListener;

    public void setLoadListener(ILoadListener<Share> loadListener) {
        this.loadListener = loadListener;
    }

    public static AllShareCornerFragment newInstance(int type) {
        AllShareCornerFragment fragment = new AllShareCornerFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, type);
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
            viewModel.setType(getArguments().getInt(TAG));
            setLayoutView();
        }
        initAdapter();
        viewModel.getSharesLiveData().observe(this, shares -> {
            loadingDialog.dismiss();
            mShareAdapter.setNewData(shares);
            loadListener.onLoaded(shares);
        });
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
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(viewModel.getType()), 200);
    }
}
