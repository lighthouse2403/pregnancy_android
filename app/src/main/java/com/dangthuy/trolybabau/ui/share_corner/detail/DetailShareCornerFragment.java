package com.dangthuy.trolybabau.ui.share_corner.detail;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.databinding.FragmentShareCornerDetailBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;
import com.dangthuy.trolybabau.ui.share_corner.adapter.ShareCommentAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class DetailShareCornerFragment extends BaseFragment<ShareCornerViewModel> {
    public static final String TAG = "DetailShareCornerFragme";

    private FragmentShareCornerDetailBinding binding;
    private ShareCommentAdapter mShareCommentAdapter;

    public static DetailShareCornerFragment newInstance(Share share) {
        DetailShareCornerFragment fragment = new DetailShareCornerFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, share);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ShareCornerViewModel> provideViewModelClass() {
        return ShareCornerViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share_corner_detail;
    }

    @Override
    protected void initView() {
        binding = (FragmentShareCornerDetailBinding) getBinding();
        if (getArguments() != null) {
            viewModel.setmShare(getArguments().getParcelable(TAG));
            setLayoutView();
        }
        initAdapter();
        viewModel.getComments().observe(this, comments -> mShareCommentAdapter.setNewData(comments));
    }

    private void initAdapter() {
        mShareCommentAdapter = new ShareCommentAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mShareCommentAdapter);
    }

    private void setLayoutView() {
        binding.tvTitle.setText(viewModel.getmShare().getTitle());
    }

    @Override
    protected void setOnClickListener() {
        binding.btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
        binding.tvSend.setOnClickListener(view -> {});
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchComment();
    }
}
