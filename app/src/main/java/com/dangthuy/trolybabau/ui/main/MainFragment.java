package com.dangthuy.trolybabau.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentMainBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.main.adapter.MainAdapter;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;

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
        binding.adView.setAdSize(AdSize.BANNER);
        binding.adView.setAdUnitId(getString(R.string.banner_home_footer));
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        binding.adView.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.adView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.adView.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.adView.destroy();
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
        binding.adView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AdActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onRefreshData() {
//        viewModel.loadAds();
//        viewModel.getLiveAd().observe(this, adRequest -> binding.adView.loadAd(adRequest));
    }
}
