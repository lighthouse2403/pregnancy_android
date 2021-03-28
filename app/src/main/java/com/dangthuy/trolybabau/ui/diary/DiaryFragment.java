package com.dangthuy.trolybabau.ui.diary;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.databinding.FragmentDiaryBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.diary.adapter.DiaryAdapter;
import com.dangthuy.trolybabau.ui.diary.add.AddDiaryFragment;
import com.dangthuy.trolybabau.ui.home.HomeFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DiaryFragment extends BaseFragment<DiaryViewModel> {
    public static final String TAG = "DiaryFragment";
    private FragmentDiaryBinding binding;
    private DiaryAdapter mDiaryAdapter;

    public static DiaryFragment newInstance() {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<DiaryViewModel> provideViewModelClass() {
        return DiaryViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_diary;
    }

    @Override
    protected void initView() {
        binding = (FragmentDiaryBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView(false);
        }
        initAdapter();
        viewModel.getDiaries().observe(this, diaries -> {
            if (diaries.size() > 0) {
                setLayoutView(true);
                mDiaryAdapter.setNewData(diaries);
            }
        });
    }

    private void initAdapter() {
        mDiaryAdapter = new DiaryAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5, 0));
        binding.recyclerView.setAdapter(mDiaryAdapter);
    }

    private void setLayoutView(boolean isNoData) {
        if (!isNoData) {
            binding.recyclerView.setVisibility(View.GONE);
            binding.tvNoData.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.tvNoData.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setOnClickListener() {
        binding.btnAdd.setOnClickListener(view -> addFragment(R.id.container, AddDiaryFragment.newInstance(), AddDiaryFragment.TAG, false));
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
