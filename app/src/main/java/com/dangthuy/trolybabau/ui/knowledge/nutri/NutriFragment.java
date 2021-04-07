package com.dangthuy.trolybabau.ui.knowledge.nutri;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.common.dialog.NutriDialog;
import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.databinding.FragmentPagerCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.knowledge.KnowledgeViewModel;
import com.dangthuy.trolybabau.ui.knowledge.adapter.NutriAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class NutriFragment extends BaseFragment<KnowledgeViewModel> {
    public static final String TAG = "NutriFoodFragment";
    private FragmentPagerCommonBinding binding;
    private NutriAdapter mNutriAdapter;

    public static NutriFragment newInstance(int position) {
        NutriFragment fragment = new NutriFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<KnowledgeViewModel> provideViewModelClass() {
        return KnowledgeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentPagerCommonBinding) getBinding();
        if (getArguments() != null) {
            viewModel.setNutri(getArguments().getInt(TAG));
        }
        initAdapter();
        viewModel.getNutries().observe(this, nutris -> mNutriAdapter.setNewData(nutris));
        setLayoutView();
    }

    private void setLayoutView() {

    }

    private void initAdapter() {
        mNutriAdapter = new NutriAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5, 0));
        binding.recyclerView.setAdapter(mNutriAdapter);
    }

    @Override
    protected void setOnClickListener() {
        mNutriAdapter.setListener(this::showDialog);
    }

    private void showDialog(Nutri nutri) {
        if (getContext() != null) {
            NutriDialog dialog = new NutriDialog(getContext());
            dialog.setmNutri(nutri);
            dialog.show();
        }
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchDataNutri();
    }
}
