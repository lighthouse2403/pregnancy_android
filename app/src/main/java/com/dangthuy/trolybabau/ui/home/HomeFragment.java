package com.dangthuy.trolybabau.ui.home;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentHomeBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.born_story.BornStoryFragment;
import com.dangthuy.trolybabau.ui.home.adapter.HomeAdapter;
import com.dangthuy.trolybabau.ui.information.InfomartionViewModel;
import com.dangthuy.trolybabau.ui.information.InfomationFragment;
import com.dangthuy.trolybabau.ui.music.MusicFragment;
import com.dangthuy.trolybabau.ui.profile.ProfileFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeFragment extends BaseFragment<HomeViewModel> {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private HomeAdapter mHomeAdapter;

    public interface IUpdateListener {
        void onUpdate();
    }

    private IUpdateListener updateListener;

    public void setUpdateListener(IUpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    private final HomeAdapter.IClickItemListener onItemClickListener = item -> {
        switch (item.getTitle()) {
            case HomeViewModel.GOC_CHIA_SE:
                addFragment(R.id.container, ShareCornerFragment.newInstance(), ShareCornerFragment.TAG, false);
                break;
            case HomeViewModel.NHAC_BAU_CHO_BE:
                addFragment(R.id.container, MusicFragment.newInstance(), MusicFragment.TAG, false);
                break;
            case HomeViewModel.CAU_CHUYEN_SINH_NO:
                addFragment(R.id.container, BornStoryFragment.newInstance(), BornStoryFragment.TAG, false);
                break;
            case HomeViewModel.CAN_NANG_CUA_ME:
                addFragment(R.id.container, InfomationFragment.newInstance(InfomartionViewModel.TYPE_MOM), InfomationFragment.TAG, false);
                break;
            case HomeViewModel.THEO_DOI_SO_LAN_DAP:
                addFragment(R.id.container, InfomationFragment.newInstance(InfomartionViewModel.TYPE_BABY), InfomationFragment.TAG, false);
                break;
        }
    };

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<HomeViewModel> provideViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        binding = (FragmentHomeBinding) getBinding();
        binding.progressbar.startAnimation();
        initAdapter();
        viewModel.getHomeMenus().observe(this, homeMenus -> mHomeAdapter.setNewData(homeMenus));
    }

    private void initAdapter() {
        mHomeAdapter = new HomeAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setListener(onItemClickListener);
    }

    @Override
    protected void setOnClickListener() {
        binding.tvName.setOnClickListener(view -> gotoProfile());
        binding.btnExpect.setOnClickListener(view -> gotoProfile());
    }

    private void gotoProfile() {
        ProfileFragment fragment = ProfileFragment.newInstance(false);
        fragment.setUpdateListener(() -> updateListener.onUpdate());
        addFragment(R.id.container, fragment, ProfileFragment.TAG, false);
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
