package com.dangthuy.trolybabau.ui.settings;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Setting;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.notification.NotificationFragment;
import com.dangthuy.trolybabau.ui.settings.adapter.SettingAdapter;
import com.dangthuy.trolybabau.ui.settings.vip.VipSettingFragment;
import com.dangthuy.trolybabau.ui.settings.wallpaper.WallpaperSettingFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class SettingFragment extends BaseFragment<SettingViewModel> {
    public static final String TAG = "SettingFragment";
    private FragmentCommonBinding binding;
    private SettingAdapter mSettingAdapter;
    private final SettingAdapter.IClickListener setttingListener = item -> {
        switch (item.getTitle()) {
            case Setting.WALLPAPER:
                addFragment(R.id.container, WallpaperSettingFragment.newInstance(), WallpaperSettingFragment.TAG, false);
                break;
            case Setting.SHARE_APP:

                break;
            case Setting.VIP:
                addFragment(R.id.container, VipSettingFragment.newInstance(), VipSettingFragment.TAG, false);
                break;
        }
    };

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
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
        viewModel.getSettings().observe(this, settings -> mSettingAdapter.setNewData(settings));
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle("Phiên bản 1.0.0");
    }

    private void initAdapter() {
        mSettingAdapter = new SettingAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5,0));
        binding.recyclerView.setAdapter(mSettingAdapter);
    }

    @Override
    protected void setOnClickListener() {
        mSettingAdapter.setListener(setttingListener);
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
