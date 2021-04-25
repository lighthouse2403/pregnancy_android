package com.dangthuy.trolybabau.ui.baby_name.detail;

import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyName;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.baby_name.BabyNameViewModel;
import com.dangthuy.trolybabau.ui.baby_name.adapter.BabyNameAdapter;
import com.dangthuy.trolybabau.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class DetailBabyNameFragment extends BaseFragment<BabyNameViewModel> {
    public static final String TAG = "DetailBabyNameFragment";
    private static final String DATA = "data";
    private static final String CHARACTER = "character";
    private static final String SPINNER = "spinner";
    private FragmentPagerCommonBinding binding;
    private BabyNameAdapter mBabyNameAdapter;
    private BabyNameAdapter.ILoveListener loveListener = (item, position) -> {
        viewModel.saveItem(item, position);
    };

    public static DetailBabyNameFragment newInstance(int position, ArrayList<BabyName> data, String firstName, boolean isSpinner) {
        DetailBabyNameFragment fragment = new DetailBabyNameFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, position);
        args.putParcelableArrayList(DATA, data);
        args.putString(CHARACTER, firstName);
        args.putBoolean(SPINNER, isSpinner);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyNameViewModel> provideViewModelClass() {
        return BabyNameViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentPagerCommonBinding) getBinding();
        if (getArguments() != null) {
            if (getArguments().getParcelableArrayList(DATA) != null) {
                viewModel.setBabyNames(getArguments().getParcelableArrayList(DATA));
                viewModel.setTab(getArguments().getInt(TAG));
                viewModel.setCharacter(getArguments().getString(CHARACTER));
                viewModel.setIsSpinner(getArguments().getBoolean(SPINNER));
            }
        }
        initAdapter();
        viewModel.getBabyNameDetails().observe(this, babyNameDetails -> {
            mBabyNameAdapter.setNewData(babyNameDetails);
        });
        viewModel.getBabyNameDetail().observe(this, babyNameDetail -> {
            mBabyNameAdapter.notifyItemChanged(viewModel.getmPosition());
        });
    }

    private void initAdapter() {
        mBabyNameAdapter = new BabyNameAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mBabyNameAdapter);
        mBabyNameAdapter.setLoveListener(loveListener);
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchBabyName();
    }
}
