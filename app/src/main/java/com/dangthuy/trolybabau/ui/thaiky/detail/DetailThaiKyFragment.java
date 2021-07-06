package com.dangthuy.trolybabau.ui.thaiky.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.data.model.Pregnancy;
import com.dangthuy.trolybabau.databinding.FragmentThaikyDetailBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.thaiky.ThaikyViewModel;
import com.dangthuy.trolybabau.ui.thaiky.adapter.ThaiKyDetailAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DetailThaiKyFragment extends BaseFragment<ThaikyViewModel> {
    public static final String TAG = "DetailThaiKyFragment";
    private static final String PREGNANCY = "pregnancy";
    private static final String BABYINDEX = "baby_index";
    private FragmentThaikyDetailBinding binding;
    private ThaiKyDetailAdapter mThaiKyDetailAdapter;

    public static DetailThaiKyFragment newInstance(int position, Pregnancy pregnancy, BabyIndex babyIndex) {
        DetailThaiKyFragment fragment = new DetailThaiKyFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, position);
        args.putParcelable(PREGNANCY, pregnancy);
        args.putParcelable(BABYINDEX, babyIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ThaikyViewModel> provideViewModelClass() {
        return ThaikyViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_thaiky_detail;
    }

    @Override
    protected void initView() {
        binding = (FragmentThaikyDetailBinding) getBinding();
        if (getArguments() != null) {
            viewModel.setPosition(getArguments().getInt(TAG));
            if (getArguments().getParcelable(PREGNANCY) != null) {
                viewModel.setPregnancy(getArguments().getParcelable(PREGNANCY));
                if (getArguments().getParcelable(BABYINDEX) != null) {
                    viewModel.setmBabyIndex(getArguments().getParcelable(BABYINDEX));
                    setLayoutView();
                    viewModel.fetchDetail();
                }
            }
        }
        initAdapter();
        viewModel.getThaikyDetails().observe(this, thaiKyDetails -> mThaiKyDetailAdapter.setNewData(thaiKyDetails));
    }

    private void initAdapter() {
        mThaiKyDetailAdapter = new ThaiKyDetailAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(10, 0));
        binding.recyclerView.setAdapter(mThaiKyDetailAdapter);
    }

    private void setLayoutView() {
        String imageName = "week_" + (viewModel.getPosition() + 1);
        int id = getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());
        binding.ivImage.setImageResource(id);

        binding.tvTime.setText(String.format(getString(R.string.tv_tuan_s), viewModel.getmPregnancy().getWeek()));
        binding.tvWeight.setText(viewModel.getmBabyIndex().getEfwGh());
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
    }
}
