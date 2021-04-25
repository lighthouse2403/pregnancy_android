package com.dangthuy.trolybabau.ui.baby_name;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.BabyName;
import com.dangthuy.trolybabau.databinding.FragmentBabyNameBinding;
import com.dangthuy.trolybabau.ui.baby_name.adapter.BabyNamePagerAdapter;
import com.dangthuy.trolybabau.ui.baby_name.favorite.FavoriteBabyNameFragment;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.born_story.BornStoryFragment;
import com.dangthuy.trolybabau.ui.home.HomeViewModel;
import com.dangthuy.trolybabau.ui.information.adapter.InformationPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameFragment extends BaseFragment<BabyNameViewModel> {
    public static final String TAG = "BabyNameFragment";
    private FragmentBabyNameBinding binding;
    private BabyNamePagerAdapter mBabyNamePagerAdapter;

    public static BabyNameFragment newInstance() {
        BabyNameFragment fragment = new BabyNameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyNameViewModel> provideViewModelClass() {
        return BabyNameViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_baby_name;
    }

    @Override
    protected void initView() {
        binding = (FragmentBabyNameBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        viewModel.getBabyNames().observe(this, babyNames -> {
            loadingDialog.dismiss();
            viewModel.setBabyNames(babyNames);
            setupViewPager("A", true);
        });
    }

    private void setLayoutView() {
        binding.toolBar.setTitle(HomeViewModel.TEN_HAY_CHO_BE);
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    setupViewPager(newText, false);
                }
                return true;
            }
        });
    }

    private void setupViewPager(String word, boolean isSpinner) {
        mBabyNamePagerAdapter = new BabyNamePagerAdapter(getChildFragmentManager(), (ArrayList<BabyName>) viewModel.getBabyNameList(), word, isSpinner);
        binding.viewPager.setAdapter(mBabyNamePagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        customTabs();
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
                            title.setText(R.string.tv_be_trai);
                            break;
                        case 1:
                            title.setText(R.string.tv_be_gai);
                            break;
                    }
                }
            }
        }
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        binding.btnLove.setOnClickListener(view -> addFragment(R.id.container, FavoriteBabyNameFragment.newInstance(), FavoriteBabyNameFragment.TAG, false));
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                mBabyNamePagerAdapter.notifyDataSetChanged();
//                customTabs();
                setupViewPager(binding.spinner.getSelectedItem().toString(), true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(), 1000);
    }
}
