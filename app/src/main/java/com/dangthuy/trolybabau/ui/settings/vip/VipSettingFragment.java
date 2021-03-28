package com.dangthuy.trolybabau.ui.settings.vip;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentSettingVipBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.settings.SettingViewModel;
import com.dangthuy.trolybabau.ui.settings.wallpaper.WallpaperSettingFragment;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class VipSettingFragment extends BaseFragment<SettingViewModel> {
    public static final String TAG = "VipSettingFragment";
    private FragmentSettingVipBinding binding;
    public static VipSettingFragment newInstance() {
        VipSettingFragment fragment = new VipSettingFragment();
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
        return R.layout.fragment_setting_vip;
    }

    @Override
    protected void initView() {
        binding = (FragmentSettingVipBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_nhap_ma));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        binding.btnConfirm.setOnClickListener(view -> {});
    }

    @Override
    protected void onRefreshData() {

    }
}
