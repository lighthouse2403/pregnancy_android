package com.dangthuy.trolybabau.ui.music;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.HomeViewModel;
import com.dangthuy.trolybabau.ui.music.adapter.MusicAdapter;
import com.dangthuy.trolybabau.ui.profile.ProfileFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/25/2021.
 */
public class MusicFragment extends BaseFragment<MusicViewModel> {
    public static final String TAG = "MusicFragment";
    private FragmentCommonBinding binding;
    private MusicAdapter mMusicAdapter;

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<MusicViewModel> provideViewModelClass() {
        return MusicViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentCommonBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        initAdapter();
        viewModel.getMusic().observe(this, music -> mMusicAdapter.setNewData(music));
    }

    private void initAdapter() {
        mMusicAdapter = new MusicAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5, 0));
        binding.recyclerView.setAdapter(mMusicAdapter);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(HomeViewModel.NHAC_BAU_CHO_BE);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());

    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
