package com.dangthuy.trolybabau.ui.born_story;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.BornStory;
import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.born_story.adapter.BornStoryAdapter;
import com.dangthuy.trolybabau.ui.born_story.detail.DetailBornStoryFragment;
import com.dangthuy.trolybabau.ui.home.HomeFragment;
import com.dangthuy.trolybabau.ui.home.HomeViewModel;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/24/2021.
 */
public class BornStoryFragment extends BaseFragment<BornStoryViewModel> {
    public static final String TAG = "BornStoryFragment";
    private BornStoryAdapter mBornStoryAdapter;
    private FragmentCommonBinding binding;

    public static BornStoryFragment newInstance() {
        BornStoryFragment fragment = new BornStoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BornStoryViewModel> provideViewModelClass() {
        return BornStoryViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentCommonBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        initAdapter();
        viewModel.getBornStories().observe(this, bornStories -> mBornStoryAdapter.setNewData(bornStories));
    }

    private void initAdapter() {
        mBornStoryAdapter = new BornStoryAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5, 0));
        binding.recyclerView.setAdapter(mBornStoryAdapter);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(HomeViewModel.CAU_CHUYEN_SINH_NO);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        mBornStoryAdapter.setListener(item -> addFragment(R.id.container, DetailBornStoryFragment.newInstance(item), DetailBornStoryFragment.TAG, false));
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
