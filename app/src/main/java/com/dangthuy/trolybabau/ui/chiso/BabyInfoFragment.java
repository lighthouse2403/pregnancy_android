package com.dangthuy.trolybabau.ui.chiso;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentInfomationCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.chiso.adapter.BabyInfoPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by nhongthai on 21/03/2021.
 */
public class BabyInfoFragment extends BaseFragment<BabyInforViewModel> {
    public static final String TAG = "BabyInfoFragment";
    private FragmentInfomationCommonBinding binding;
    private BabyInfoPagerAdapter mBabyInfoPagerAdapter;
    private ToolBar.OnItemToolBarClickListener onToolBarClickListener = item -> {
        switch (item) {
            case ADD:

                break;
            case BACK:
                getParentFragmentManager().popBackStack();
                break;
        }
    };

    public static BabyInfoFragment newInstance(boolean isTab) {
        BabyInfoFragment fragment = new BabyInfoFragment();
        Bundle args = new Bundle();
        args.putBoolean(TAG, isTab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyInforViewModel> provideViewModelClass() {
        return BabyInforViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_infomation_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentInfomationCommonBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView(getArguments().getBoolean(TAG));
        }
        setupViewPager();
    }

    private void setupViewPager() {
        mBabyInfoPagerAdapter = new BabyInfoPagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(mBabyInfoPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        customTabs();
        binding.tabs.addOnTabSelectedListener(onTabLayout);
        binding.viewPager.setOffscreenPageLimit(2);

    }

    private void setLayoutView(boolean isTab) {
        binding.toolBar.setLayoutView(isTab ? ToolBarType.BABY_INFO_TAB : ToolBarType.INFOMATION);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(onToolBarClickListener);
    }

    @Override
    protected void onRefreshData() {

    }

    private final TabLayout.OnTabSelectedListener onTabLayout = new TabLayout.OnTabSelectedListener() {
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
//                title.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_tab));
            } else {
//                title.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_edittext_profile));
            }
        }
    }

    private void customTabs() {
        for (int i = 0; i < 5; i++) {
            TabLayout.Tab tab = binding.tabs.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.custom_tab);
                View view = tab.getCustomView();
                if (view != null) {
                    AppCompatTextView title = view.findViewById(R.id.tvTitle);
                    switch (i) {
                        case 0:
                            title.setText(R.string.tv_danh_sach);
//                            title.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_tab));
                            break;
                        case 1:
                            title.setText(R.string.tv_bieu_do);
                            break;
                    }
                }
            }
        }
    }
}
