package com.dangthuy.trolybabau.ui.clothes.hospital;

import android.os.Bundle;
import android.os.Handler;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Clothes;
import com.dangthuy.trolybabau.data.model.ClothesSection;
import com.dangthuy.trolybabau.databinding.FragmentClothesHospitalBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.clothes.ClothesViewModel;
import com.dangthuy.trolybabau.ui.clothes.adapter.HospitalAdapter;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class HospitalClothesFragment extends BaseFragment<ClothesViewModel> {
    public static final String TAG = "HospitalClothesFragment";
    private FragmentClothesHospitalBinding binding;
    private HospitalAdapter mHospitalAdapter;

    public static HospitalClothesFragment newInstance() {
        HospitalClothesFragment fragment = new HospitalClothesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ClothesViewModel> provideViewModelClass() {
        return ClothesViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_clothes_hospital;
    }

    @Override
    protected void initView() {
        binding = (FragmentClothesHospitalBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        viewModel.getSections().observe(this, clothesSections -> {
            loadingDialog.dismiss();
            initAdapter(clothesSections);
        });
    }

    private void initAdapter(List<ClothesSection> sections) {
        mHospitalAdapter = new HospitalAdapter();
        mHospitalAdapter.setData(sections);
        binding.recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        binding.recyclerView.setAdapter(mHospitalAdapter);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_do_mang_vao_vien));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(), 300);
    }
}
