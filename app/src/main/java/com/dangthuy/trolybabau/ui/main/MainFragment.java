package com.dangthuy.trolybabau.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentMainBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.main.adapter.MainAdapter;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class MainFragment extends BaseFragment<MainViewModel> {
    private static final String TAG = "MainFragment";

    private FragmentMainBinding binding;
    private MainAdapter mMainAdapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<MainViewModel> provideViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        binding = (FragmentMainBinding) getBinding();
        setupViewPager();
        setupBottomBar();
    }

    private void setupBottomBar() {
        binding.bottomNavigation.setItemIconTintList(null);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            for (int i = 0; i < getParentFragmentManager().getBackStackEntryCount(); i++) {
                getParentFragmentManager().popBackStack();
            }
            switch (item.getItemId()) {
                case R.id.navTrangChu:
                    binding.viewPager.setCurrentItem(0);
                    return true;
                case R.id.navThaiky:
                    binding.viewPager.setCurrentItem(1);
                    return true;
                case R.id.navNhatky:
                    binding.viewPager.setCurrentItem(2);
                    return true;
                case R.id.navChiso:
                    binding.viewPager.setCurrentItem(3);
                    return true;
                case R.id.navKienthuc:
                    binding.viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        });
    }

    private void setupViewPager() {
        mMainAdapter = new MainAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(mMainAdapter);
        binding.viewPager.setOffscreenPageLimit(5);
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }
}
