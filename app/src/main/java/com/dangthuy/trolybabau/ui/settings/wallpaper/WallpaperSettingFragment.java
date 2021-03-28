package com.dangthuy.trolybabau.ui.settings.wallpaper;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.settings.SettingFragment;
import com.dangthuy.trolybabau.ui.settings.SettingViewModel;
import com.dangthuy.trolybabau.ui.settings.adapter.WallpaperSettingAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class WallpaperSettingFragment extends BaseFragment<SettingViewModel> {
    public static final String TAG = "WallpaperSettingFragmen";
    private FragmentCommonBinding binding;
    private WallpaperSettingAdapter mWallpaperSettingAdapter;

    public static WallpaperSettingFragment newInstance() {
        WallpaperSettingFragment fragment = new WallpaperSettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<SettingViewModel> provideViewModelClass() {
        return SettingViewModel.class;
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
        viewModel.getWallpapers().observe(this, wallpapers -> mWallpaperSettingAdapter.setNewData(wallpapers));
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_cai_dat_mau_nen));
    }

    private void initAdapter() {
        mWallpaperSettingAdapter = new WallpaperSettingAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mWallpaperSettingAdapter);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        mWallpaperSettingAdapter.setListener((item, pos) -> {
            viewModel.changeWallpaper(item, pos);
        });
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchDataWallpaper();
    }
}
