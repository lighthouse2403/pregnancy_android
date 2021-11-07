package com.dangthuy.trolybabau.ui.knowledge;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.adapter.HomeAdapter;
import com.dangthuy.trolybabau.ui.knowledge.section.KnowledgeFragment;
import com.dangthuy.trolybabau.ui.knowledge.section.NutriKnowledgeFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class KnowledgePageFragment extends BaseFragment<KnowledgeViewModel> {
    public static final String TAG = "KnowledgePageFragment";
    private FragmentCommonBinding binding;
    private HomeAdapter mHomeAdapter;
    private final HomeAdapter.IClickItemListener knowledgeListener = item -> {
        switch (item.getTitle()) {
            case KnowledgeViewModel.TRUOC_THAI_KY:
            case KnowledgeViewModel.TRONG_THAI_KY:
            case KnowledgeViewModel.CHUYEN_DA_VA_DA_SINH:
            case KnowledgeViewModel.SAU_SINH:
            case KnowledgeViewModel.CHIA_SE_KINH_NGHIEM:
                addFragment(R.id.container, KnowledgeFragment.newInstance(item.getTitle()), KnowledgeFragment.TAG, false);
                break;
            case KnowledgeViewModel.DINH_DUONG:
                addFragment(R.id.container, NutriKnowledgeFragment.newInstance(), NutriKnowledgeFragment.TAG, false);
                break;
        }
    };

    public static KnowledgePageFragment newInstance() {
        KnowledgePageFragment fragment = new KnowledgePageFragment();
        Bundle args = new Bundle();
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
            setLayoutView();
        }
        initAdapter();
        viewModel.getMenus().observe(this, homeMenus -> mHomeAdapter.setNewData(homeMenus));
    }

    private void setLayoutView() {
        binding.clContent.setBackgroundColor(getContext().getResources().getColor(R.color.transparent_background));
        binding.toolBar.setTitle(getString(R.string.tv_kien_thuc));
    }

    private void initAdapter() {
        mHomeAdapter = new HomeAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(20, 20));
        binding.recyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected void setOnClickListener() {
        mHomeAdapter.setListener(knowledgeListener);
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
