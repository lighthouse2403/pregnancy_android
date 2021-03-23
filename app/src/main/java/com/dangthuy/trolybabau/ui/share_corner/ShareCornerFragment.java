package com.dangthuy.trolybabau.ui.share_corner;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentShareCornerBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.adapter.ShareCornerPagerAdapter;
import com.dangthuy.trolybabau.ui.share_corner.add.AddShareCornerFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    public static final String TAG = "ShareCornerFragment";

    private FragmentShareCornerBinding binding;
    private ShareCornerPagerAdapter mShareCornerPagerAdapter;

    private ToolBar.OnItemToolBarClickListener onToolBarItemClickListener = item -> {
        switch (item) {
            case BACK:
                getParentFragmentManager().popBackStack();
                break;
            case ADD:
                addFragment(R.id.container, AddShareCornerFragment.newInstance(), AddShareCornerFragment.TAG, false);
                break;
        }
    };
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

    public static ShareCornerFragment newInstance() {
        ShareCornerFragment fragment = new ShareCornerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ShareCornerViewModel> provideViewModelClass() {
        return ShareCornerViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share_corner;
    }

    @Override
    protected void initView() {
        binding = (FragmentShareCornerBinding) getBinding();
        setLayoutView();
        setupViewPager();
    }

    private void setupViewPager() {
        mShareCornerPagerAdapter = new ShareCornerPagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(mShareCornerPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        customTabs();
        binding.tabs.addOnTabSelectedListener(onTabLayout);
        binding.viewPager.setOffscreenPageLimit(2);
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
                            title.setText(R.string.tv_all);
                            title.setTextColor(requireContext().getColor(R.color.green));
                            break;
                        case 1:
                            title.setText(R.string.tv_my_share);
                            break;
                        case 2:
                            title.setText(R.string.tv_hot);
                            break;
                        case 3:
                            title.setText(R.string.tv_love);
                            break;
                        case 4:
                            title.setText(R.string.tv_bai_viet_trong_tuan);
                            break;
                    }
                }
            }
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.BABY_INFO);
        binding.toolBar.setTitle(getString(R.string.tv_goc_chia_se));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(onToolBarItemClickListener);
    }

    @Override
    protected void onRefreshData() {

    }
}
