package com.dangthuy.trolybabau.ui.share_corner.add;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.response.ThreadResponse;
import com.dangthuy.trolybabau.databinding.FragmentShareCornerAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;
import com.dangthuy.trolybabau.ui.share_corner.all.AllShareCornerFragment;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class AddShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    public static final String TAG = "AddShareCornerFragment";
    private FragmentShareCornerAddBinding binding;

    public static AddShareCornerFragment newInstance() {
        AddShareCornerFragment fragment = new AddShareCornerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ShareCornerViewModel> provideViewModelClass() {
        return ShareCornerViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share_corner_add;
    }

    @Override
    protected void initView() {
        binding = (FragmentShareCornerAddBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        viewModel.getLiveToast().observe(this, this::showToast);
        viewModel.getLiveThread().observe(this, this::process);
    }

    private void process(ThreadResponse threadResponse) {
        if (threadResponse.getError() == null) {
            getParentFragmentManager().popBackStack();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_goc_chia_se));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        binding.btnConfirm.setOnClickListener(view -> viewModel.createShare(binding.etTitle.getText().toString(), binding.etContent.getText().toString()));
    }

    @Override
    protected void onRefreshData() {

    }
}
