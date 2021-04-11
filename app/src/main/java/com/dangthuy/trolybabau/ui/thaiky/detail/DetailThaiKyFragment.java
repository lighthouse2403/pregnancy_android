package com.dangthuy.trolybabau.ui.thaiky.detail;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import com.bumptech.glide.Glide;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.data.model.Pregnancy;
import com.dangthuy.trolybabau.databinding.FragmentThaikyDetailBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.thaiky.ThaikyViewModel;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DetailThaiKyFragment extends BaseFragment<ThaikyViewModel> {
    public static final String TAG = "DetailThaiKyFragment";
    private static final String PREGNANCY = "pregnancy";
    private static final String BABYINDEX = "baby_index";
    private FragmentThaikyDetailBinding binding;

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
            if (getArguments().getParcelable(PREGNANCY) != null) {
                viewModel.setPregnancy(getArguments().getParcelable(PREGNANCY));
                if (getArguments().getParcelable(BABYINDEX) != null) {
                    viewModel.setmBabyIndex(getArguments().getParcelable(BABYINDEX));
                    setLayoutView();
                }
            }
        }
    }

    private void setLayoutView() {
//        binding.tvContent.setText(viewModel.getmItem().getMom());
        Glide.with(this).load(R.drawable.babyyearold).into(binding.ivImage);
        binding.tvTime.setText(String.format(getString(R.string.tv_tuan_s), viewModel.getmPregnancy().getWeek()));
        binding.tvWeight.setText(viewModel.getmBabyIndex().getEfwGh());
        binding.tvContent.setText(viewModel.getmPregnancy().getMom());
        binding.tvContent.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
    }
}
