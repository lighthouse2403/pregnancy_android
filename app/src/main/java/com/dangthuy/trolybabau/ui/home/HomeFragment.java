package com.dangthuy.trolybabau.ui.home;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentHomeBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.adapter.HomeAdapter;
import com.dangthuy.trolybabau.ui.main.MainFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeFragment extends BaseFragment<HomeViewModel> {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private HomeAdapter mHomeAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<HomeViewModel> provideViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        binding = (FragmentHomeBinding) getBinding();
        binding.progressbar.startAnimation();
        initAdapter();
        viewModel.getHomeMenus().observe(this, homeMenus -> mHomeAdapter.setNewData(homeMenus));
    }

    private void initAdapter() {
        mHomeAdapter = new HomeAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
