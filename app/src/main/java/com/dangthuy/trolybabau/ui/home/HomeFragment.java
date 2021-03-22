package com.dangthuy.trolybabau.ui.home;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.databinding.FragmentHomeBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.adapter.HomeAdapter;
import com.dangthuy.trolybabau.ui.main.MainFragment;
import com.dangthuy.trolybabau.ui.profile.ProfileFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeFragment extends BaseFragment<HomeViewModel> {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private HomeAdapter mHomeAdapter;
    private final HomeAdapter.IClickItemListener onItemClickListener = item -> {
        switch (item.getTitle()) {
            case HomeMenu.GOC_CHIA_SE:
                addFragment(R.id.container, ShareCornerFragment.newInstance(), ShareCornerFragment.TAG, false);
                break;
            case HomeMenu.NHAC_CHO_THAI_NHI:
                break;
        }
    };

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
        mHomeAdapter.setListener(onItemClickListener);
    }

    @Override
    protected void setOnClickListener() {
        binding.tvName.setOnClickListener(view -> gotoProfile());
        binding.btnExpect.setOnClickListener(view -> gotoProfile());
    }

    private void gotoProfile() {
        addFragment(R.id.container, ProfileFragment.newInstance(false), ProfileFragment.TAG, false);
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
