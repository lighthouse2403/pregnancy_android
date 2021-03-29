package com.dangthuy.trolybabau.ui.chiso.list;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInfoFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInforViewModel;
import com.dangthuy.trolybabau.ui.chiso.adapter.ListBabyInfoAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ListBabyInfoFragment extends BaseFragment<BabyInforViewModel> {
    public static final String TAG = "ListBabyInfoFragment";
    private FragmentPagerCommonBinding binding;
    private ListBabyInfoAdapter mListBabyInfoAdapter;

    public static ListBabyInfoFragment newInstance() {
        ListBabyInfoFragment fragment = new ListBabyInfoFragment();
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
        return R.layout.fragment_pager_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentPagerCommonBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        initAdapter();
        viewModel.getBabyInfos().observe(this, babyInfos -> mListBabyInfoAdapter.setNewData(babyInfos));
    }

    private void initAdapter() {
        mListBabyInfoAdapter = new ListBabyInfoAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5,0));
        binding.recyclerView.setAdapter(mListBabyInfoAdapter);
    }

    private void setLayoutView() {

    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchDataListBabyInfo();
    }
}
