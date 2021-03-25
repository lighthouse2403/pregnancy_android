package com.dangthuy.trolybabau.ui.born_story.detail;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentDetailCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.born_story.BornStoryViewModel;

/**
 * Created by nhongthai on 3/25/2021.
 */
public class DetailBornStoryFragment extends BaseFragment<BornStoryViewModel> {
    public static final String TAG = "DetailBornStoryFragment";
    private FragmentDetailCommonBinding binding;
    public static DetailBornStoryFragment newInstance() {
        DetailBornStoryFragment fragment = new DetailBornStoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected Class<BornStoryViewModel> provideViewModelClass() {
        return BornStoryViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentDetailCommonBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle("bla");
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {

    }
}
