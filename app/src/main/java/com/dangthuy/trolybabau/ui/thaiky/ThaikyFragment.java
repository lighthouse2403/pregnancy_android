package com.dangthuy.trolybabau.ui.thaiky;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentThaikyBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.main.MainFragment;
import com.dangthuy.trolybabau.ui.notification.NotificationFragment;
import com.dangthuy.trolybabau.ui.settings.SettingFragment;
import com.dangthuy.trolybabau.ui.settings.SettingViewModel;
import com.dangthuy.trolybabau.ui.share_corner.adapter.ShareCornerPagerAdapter;
import com.dangthuy.trolybabau.ui.thaiky.adapter.ThaiKyPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ThaikyFragment extends BaseFragment<ThaikyViewModel> {
    private static final String TAG = "ThaikyFragment";
    private FragmentThaikyBinding binding;
    private ThaiKyPagerAdapter mThaiKyPagerAdapter;
    private final ToolBar.OnItemToolBarClickListener toolBarListener = item -> {
        switch (item) {
            case NOTIFY:
                addFragment(R.id.container, NotificationFragment.newInstance(), NotificationFragment.TAG, false);
                break;
            case SETUP:
                addFragment(R.id.container, SettingFragment.newInstance(), SettingFragment.TAG, false);
                break;
        }
    };

    public static ThaikyFragment newInstance() {
        ThaikyFragment fragment = new ThaikyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ThaikyViewModel> provideViewModelClass() {
        return ThaikyViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_thaiky;
    }

    @Override
    protected void initView() {
        binding = (FragmentThaikyBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        viewModel.getPregnancies().observe(this, pregnancies -> {
            loadingDialog.dismiss();
            viewModel.setPregnancies(pregnancies);
            setupViewPager();
        });
        viewModel.getBabyIndexs().observe(this, babyIndices -> {
            viewModel.setBabyIndexList(babyIndices);
            setupViewPager();
        });
    }

    private void setupViewPager() {
        if (viewModel.getPregnancyList() != null && viewModel.getBabyIndexList() != null) {
            mThaiKyPagerAdapter = new ThaiKyPagerAdapter(getChildFragmentManager(), viewModel.getPregnancyList(), viewModel.getBabyIndexList());
            binding.viewPager.setAdapter(mThaiKyPagerAdapter);
            binding.tabs.setupWithViewPager(binding.viewPager);
            customTabs();
            binding.tabs.addOnTabSelectedListener(onTabLayout);
            binding.viewPager.setOffscreenPageLimit(3);
            binding.viewPager.setCurrentItem(viewModel.getWeek() + 1);
        }
    }

    private TabLayout.OnTabSelectedListener onTabLayout = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            pickTab(tab, true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            pickTab(tab, false);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private void pickTab(TabLayout.Tab tab, boolean isPick) {
        View view = tab.getCustomView();
        if (view != null) {
            AppCompatTextView title = view.findViewById(R.id.tvTitle);
            if (isPick) {
                title.setTextColor(requireContext().getColor(R.color.green));
            } else {
                title.setTextColor(requireContext().getColor(R.color.black));
            }
        }
    }


    private void customTabs() {
        for (int i = 0; i < binding.tabs.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabs.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.thaiky_tab);
                View view = tab.getCustomView();
                if (view != null) {
                    AppCompatTextView title = view.findViewById(R.id.tvTitle);
                    AppCompatTextView date = view.findViewById(R.id.tvDate);
                    title.setText(String.format(getString(R.string.tv_tuan_s), String.valueOf(i + 1)));
                    date.setText(viewModel.getDate(i + 1));
                }
            }
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.PREGNANT_TIME);

    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(toolBarListener);
    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(), 2000);
    }
}
