package com.dangthuy.trolybabau.ui.share_corner.detail;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.databinding.FragmentShareCornerDetailBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.share_corner.ShareCornerViewModel;
import com.dangthuy.trolybabau.ui.share_corner.adapter.ShareCommentAdapter;

import java.util.ArrayList;
import java.util.Date;

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
            viewModel.viewShare();
            setLayoutView();
        }
        initAdapter();
        viewModel.getLiveComment().observe(this, comments -> mShareCommentAdapter.setNewData(comments));
        viewModel.getLiveLoveError().observe(this, response -> {
            if (response.getError() == null) {
                mShareCommentAdapter.notifyItemChanged(response.getPosition());
            }
        });
        viewModel.getLiveStarError().observe(this, response -> {
            if (response.getError() == null) {
                binding.ivStar.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_star_love));
                binding.ivStar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            } else {
                binding.ivStar.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_star));
                binding.ivStar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    private void initAdapter() {
        mShareCommentAdapter = new ShareCommentAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mShareCommentAdapter);
    }

    private void setLayoutView() {
        binding.tvTitle.setText(viewModel.getmShare().getTitle());
        binding.tvName.setText(viewModel.getmShare().getOwner());
        binding.tvContent.setText(viewModel.getmShare().getContent());
        binding.tvTime.setText(DateUtils.formatDate(new Date(viewModel.getmShare().getTime())));
        if (viewModel.getmShare().getFavorite() != null && viewModel.getmShare().getFavorite().length() > 0) {
            binding.ivStar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_star_love));
            binding.ivStar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        } else {
            binding.ivStar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_star));
            binding.ivStar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
//            binding.ivStar.setColorFilter(getContext().getResources().getColor(R.color.white));
        }
    }

    @Override
    protected void setOnClickListener() {
        binding.btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
        binding.tvSend.setOnClickListener(view -> {
            if (binding.etComment.getText().toString() != null && !binding.etComment.getText().toString().isEmpty()) {
                viewModel.sendComment(viewModel.getmShare(), binding.etComment.getText().toString());
            }
        });
        mShareCommentAdapter.setItemListener((item, position, status) -> {
            viewModel.sendLove(viewModel.getmShare().getKey(), item, position);
        });
        binding.ivStar.setOnClickListener(view -> viewModel.doStar());
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchComment(viewModel.getmShare().getKey());
    }
}
