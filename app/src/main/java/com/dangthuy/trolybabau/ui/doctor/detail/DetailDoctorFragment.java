package com.dangthuy.trolybabau.ui.doctor.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Doctor;
import com.dangthuy.trolybabau.data.model.DoctorComment;
import com.dangthuy.trolybabau.databinding.FragmentDoctorDetailBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.doctor.DoctorViewModel;
import com.dangthuy.trolybabau.ui.doctor.adapter.DoctorCommentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 11/7/2021.
 */
public class DetailDoctorFragment extends BaseFragment<DoctorViewModel> {
    public static final String TAG = "DetailDoctorFragment";
    private FragmentDoctorDetailBinding binding;
    private DoctorCommentAdapter mDoctorCommentAdapter;

    public static DetailDoctorFragment newInstance(Doctor doctor) {
        DetailDoctorFragment fragment = new DetailDoctorFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, doctor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<DoctorViewModel> provideViewModelClass() {
        return DoctorViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_doctor_detail;
    }

    @Override
    protected void initView() {
        binding = (FragmentDoctorDetailBinding) getBinding();
        if (getArguments() != null) {
            if (getArguments().getParcelable(TAG) != null) {
                viewModel.setDoctor(getArguments().getParcelable(TAG));
            }
        }
        initAdapter();
        setLayoutView();
        setObserve();
    }

    private void setObserve() {
        viewModel.getLiveDoctorComment().observe(this, this::processComment);
    }

    private void processComment(List<DoctorComment> doctorComments) {
        if (doctorComments != null && doctorComments.size() > 0) {
            mDoctorCommentAdapter.setNewData(doctorComments);
        }
    }

    private void initAdapter() {
        mDoctorCommentAdapter = new DoctorCommentAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mDoctorCommentAdapter);
        
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_thong_tin_bac_si));
        Bitmap placeholder = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.doctor_list);
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), placeholder);
        circularBitmapDrawable.setCircular(true);
        final int resourceId = getContext().getResources().getIdentifier(viewModel.getDoctor().getImage(), "drawable", getContext().getPackageName());
        Glide.with(getContext()).load(resourceId)
                .error(circularBitmapDrawable)
                .placeholder(circularBitmapDrawable)
                .transform(new CircleCrop())
                .into(binding.ivImage);
        binding.tvName.setText(viewModel.getDoctor().getName());
        if (viewModel.getDoctor().getStarRank() > 0) {
            binding.rate.setVisibility(View.VISIBLE);
            binding.rate.setRating(viewModel.getDoctor().getStarRank());
        } else {
            binding.rate.setVisibility(View.GONE);
        }
        if (viewModel.getDoctor().getFullDescription() != null) {
            binding.tvContent.setText(viewModel.getDoctor().getFullDescription());
        }
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchComment();
    }
}
