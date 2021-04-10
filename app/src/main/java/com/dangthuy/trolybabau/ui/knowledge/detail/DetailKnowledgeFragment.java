package com.dangthuy.trolybabau.ui.knowledge.detail;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Knowledge;
import com.dangthuy.trolybabau.databinding.FragmentDetailCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.HomeFragment;
import com.dangthuy.trolybabau.ui.knowledge.KnowledgeViewModel;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DetailKnowledgeFragment extends BaseFragment<KnowledgeViewModel> {
    public static final String TAG = "DetailKnowledgeFragment";
    private FragmentDetailCommonBinding binding;

    public static DetailKnowledgeFragment newInstance(Knowledge item) {
        DetailKnowledgeFragment fragment = new DetailKnowledgeFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<KnowledgeViewModel> provideViewModelClass() {
        return KnowledgeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentDetailCommonBinding) getBinding();
        if (getArguments() != null) {
            if (getArguments().getParcelable(TAG) != null) {
                viewModel.setItemKnowledge(getArguments().getParcelable(TAG));
            }
            setLayoutView();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(viewModel.getmItem().getTitle());
        binding.tvContent.setText(viewModel.getmItem().getContent());
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {

    }
}
