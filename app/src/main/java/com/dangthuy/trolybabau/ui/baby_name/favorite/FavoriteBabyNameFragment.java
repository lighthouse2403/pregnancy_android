package com.dangthuy.trolybabau.ui.baby_name.favorite;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.baby_name.BabyNameFragment;
import com.dangthuy.trolybabau.ui.baby_name.BabyNameViewModel;
import com.dangthuy.trolybabau.ui.baby_name.adapter.BabyNameAdapter;
import com.dangthuy.trolybabau.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class FavoriteBabyNameFragment extends BaseFragment<BabyNameViewModel> {
    public static final String TAG = "FavoriteBabyNameFragmen";
    private FragmentCommonBinding binding;
    private BabyNameAdapter mBabyNameAdapter;

    public static FavoriteBabyNameFragment newInstance() {
        FavoriteBabyNameFragment fragment = new FavoriteBabyNameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyNameViewModel> provideViewModelClass() {
        return BabyNameViewModel.class;
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
        viewModel.getBabyNameDetails().observe(this, babyNameDetails -> mBabyNameAdapter.setNewData(babyNameDetails));
    }

    private void initAdapter() {
        mBabyNameAdapter = new BabyNameAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mBabyNameAdapter);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_yeu_thich));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchLoveBabyName();
    }
}
