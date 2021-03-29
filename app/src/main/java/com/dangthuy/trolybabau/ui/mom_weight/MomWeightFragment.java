package com.dangthuy.trolybabau.ui.mom_weight;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentInfomationCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.HomeViewModel;
import com.dangthuy.trolybabau.ui.mom_weight.adapter.MomWeightPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class MomWeightFragment extends BaseFragment<MomWeightViewModel> {
    public static final String TAG = "MomWeightFragment";
    private FragmentInfomationCommonBinding binding;
    private MomWeightPagerAdapter mMomWeightPagerAdapter;
    private final ToolBar.OnItemToolBarClickListener toolbarListener = item -> {
        switch (item) {
            case BACK:
                getParentFragmentManager().popBackStack();;
                break;
            case ADD:

                break;
        }
    };

    public static MomWeightFragment newInstance() {
        MomWeightFragment fragment = new MomWeightFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<MomWeightViewModel> provideViewModelClass() {
        return MomWeightViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_infomation_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentInfomationCommonBinding) getBinding();
        if (getArguments() != null){
            setLayoutView();
        }
        setupViewPager();
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.INFOMATION);
        binding.toolBar.setTitle(HomeViewModel.CAN_NANG_CUA_ME);
    }

    private void setupViewPager() {
        mMomWeightPagerAdapter = new MomWeightPagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(mMomWeightPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        customTabs();
        binding.tabs.addOnTabSelectedListener(onTabLayout);
        binding.viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(toolbarListener);
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
                tab.setCustomView(R.layout.custom_tab);
                View view = tab.getCustomView();
                if (view != null) {
                    AppCompatTextView title = view.findViewById(R.id.tvTitle);
                    switch (i) {
                        case 0:
                            title.setText(R.string.tv_danh_sach);
                            title.setTextColor(requireContext().getColor(R.color.green));
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
