package com.dangthuy.trolybabau.ui.knowledge.section;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentKnowledgeNutriBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.knowledge.KnowledgeViewModel;
import com.dangthuy.trolybabau.ui.knowledge.adapter.NutriKnowledgePagerAdapter;
import com.dangthuy.trolybabau.ui.share_corner.adapter.ShareCornerPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class NutriKnowledgeFragment extends BaseFragment<KnowledgeViewModel> {
    public static final String TAG = "NutriKnowledgeFragment";
    private FragmentKnowledgeNutriBinding binding;
    private NutriKnowledgePagerAdapter mNutriKnowledgePagerAdapter;

    public static NutriKnowledgeFragment newInstance() {
        NutriKnowledgeFragment fragment = new NutriKnowledgeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<KnowledgeViewModel> provideViewModelClass() {
        return KnowledgeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_nutri;
    }

    @Override
    protected void initView() {
        binding = (FragmentKnowledgeNutriBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        setupViewPager();
    }

    private void setupViewPager() {
        mNutriKnowledgePagerAdapter = new NutriKnowledgePagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(mNutriKnowledgePagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        customTabs();
        binding.tabs.addOnTabSelectedListener(onTabLayout);
        binding.viewPager.setOffscreenPageLimit(3);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_dinh_duong));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
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
                            title.setText(R.string.tv_food);
                            title.setTextColor(requireContext().getColor(R.color.green));
                            break;
                        case 1:
                            title.setText(R.string.tv_fruit);
                            break;
                        case 2:
                            title.setText(R.string.tv_vitamin);
                            break;
                    }
                }
            }
        }
    }
}
