package com.dangthuy.trolybabau.ui.knowledge.section;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.knowledge.KnowledgeViewModel;
import com.dangthuy.trolybabau.ui.knowledge.adapter.DetailKnowledgeAdapter;
import com.dangthuy.trolybabau.ui.knowledge.detail.DetailKnowledgeFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class KnowledgeFragment extends BaseFragment<KnowledgeViewModel> {
    public static final String TAG = "KnowledgeFragment";
    private FragmentCommonBinding binding;
    private DetailKnowledgeAdapter mDetailKnowledgeAdapter;

    public static KnowledgeFragment newInstance(String title) {
        KnowledgeFragment fragment = new KnowledgeFragment();
        Bundle args = new Bundle();
        args.putString(TAG, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<KnowledgeViewModel> provideViewModelClass() {
        return KnowledgeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentCommonBinding) getBinding();
        if (getArguments() != null) {
            viewModel.setMenu(getArguments().getString(TAG));
            setLayoutView(viewModel.getmMenu());
        }
        initAdapter();
        viewModel.getKnowledges().observe(this, knowledges -> mDetailKnowledgeAdapter.setNewData(knowledges));
    }

    private void initAdapter() {
        mDetailKnowledgeAdapter = new DetailKnowledgeAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(5, 0));
        binding.recyclerView.setAdapter(mDetailKnowledgeAdapter);
    }

    private void setLayoutView(String title) {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(String.format(getString(R.string.tv_kien_thuc_s), title.toLowerCase()));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        mDetailKnowledgeAdapter.setListener(item -> addFragment(R.id.container, DetailKnowledgeFragment.newInstance(), DetailKnowledgeFragment.TAG, false));
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchDataDetail();
    }
}
