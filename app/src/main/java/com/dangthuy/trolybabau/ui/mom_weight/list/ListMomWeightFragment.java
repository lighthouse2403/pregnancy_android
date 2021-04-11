package com.dangthuy.trolybabau.ui.mom_weight.list;

import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.mom_weight.InfomartionViewModel;
import com.dangthuy.trolybabau.ui.mom_weight.adapter.InformationAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ListMomWeightFragment extends BaseFragment<InfomartionViewModel> {
    public static final String TAG = "ListMomWeightFragment";
    private FragmentPagerCommonBinding binding;
    private InformationAdapter mInformationAdapter;

    public static ListMomWeightFragment newInstance() {
        ListMomWeightFragment fragment = new ListMomWeightFragment();
        Bundle args = new Bundle();
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

        }
        initAdapter();
        viewModel.getBabyFoots().observe(this, babyFoots -> {
            loadingDialog.dismiss();
            mInformationAdapter.setNewData(babyFoots);
        });
    }

    private void initAdapter() {
        mInformationAdapter = new InformationAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5,0));
        binding.recyclerView.setAdapter(mInformationAdapter);
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchBabyFootDb(),1000);
    }
}
