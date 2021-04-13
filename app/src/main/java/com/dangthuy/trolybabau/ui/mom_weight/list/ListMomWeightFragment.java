package com.dangthuy.trolybabau.ui.mom_weight.list;

import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.mom_weight.InfomartionViewModel;
import com.dangthuy.trolybabau.ui.mom_weight.adapter.BabyFootAdapter;
import com.dangthuy.trolybabau.ui.mom_weight.adapter.MomWeightAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ListMomWeightFragment extends BaseFragment<InfomartionViewModel> {
    public static final String TAG = "ListMomWeightFragment";
    private FragmentPagerCommonBinding binding;
    private BabyFootAdapter mBabyFootAdapter;
    private MomWeightAdapter mMomWeightAdapter;

    public static ListMomWeightFragment newInstance(int type) {
        ListMomWeightFragment fragment = new ListMomWeightFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<InfomartionViewModel> provideViewModelClass() {
        return InfomartionViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentPagerCommonBinding) getBinding();
        if (getArguments() != null) {
            viewModel.setType(getArguments().getInt(TAG));
        }
        initAdapter();
    }

    private void initAdapter() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5, 0));
        if (viewModel.getmType() == InfomartionViewModel.TYPE_BABY) {
            mBabyFootAdapter = new BabyFootAdapter(new ArrayList<>());
            binding.recyclerView.setAdapter(mBabyFootAdapter);
            viewModel.getBabyFoots().observe(this, babyFoots -> {
                loadingDialog.dismiss();
                mBabyFootAdapter.setNewData(babyFoots);
            });
        } else {
            mMomWeightAdapter = new MomWeightAdapter(new ArrayList<>());
            binding.recyclerView.setAdapter(mMomWeightAdapter);
            viewModel.getMomWeights().observe(this, momWeights -> {
                loadingDialog.dismiss();
                mMomWeightAdapter.setNewData(momWeights);
            });
        }
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        if (viewModel.getmType() == InfomartionViewModel.TYPE_BABY) {
            new Handler().postDelayed(() -> viewModel.fetchBabyFootDb(), 1000);
        } else if (viewModel.getmType() == InfomartionViewModel.TYPE_MOM) {
            new Handler().postDelayed(() -> viewModel.fetchMomWeightDb(), 1000);
        }
    }
}
