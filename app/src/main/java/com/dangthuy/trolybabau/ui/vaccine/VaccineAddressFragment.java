package com.dangthuy.trolybabau.ui.vaccine;

import android.os.Bundle;
import android.os.Handler;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.VaccineAddress;
import com.dangthuy.trolybabau.data.model.VaccineAddressSection;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.recipe.RecipeFragment;
import com.dangthuy.trolybabau.ui.vaccine.adapter.VaccineAddressAdapter;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class VaccineAddressFragment extends BaseFragment<VaccineAddressViewModel> {
    public static final String TAG = "VaccineAddressFragment";
    private FragmentCommonBinding binding;
    private VaccineAddressAdapter mVaccineAdapter;

    public static VaccineAddressFragment newInstance() {
        VaccineAddressFragment fragment = new VaccineAddressFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<VaccineAddressViewModel> provideViewModelClass() {
        return VaccineAddressViewModel.class;
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
        viewModel.getVaccines().observe(this, vaccineAddressSections -> {
            loadingDialog.dismiss();
            initAdapter(vaccineAddressSections);
        });
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_co_so_tiem_chung_uy_tin));
    }

    private void initAdapter(List<VaccineAddressSection> sections) {
        mVaccineAdapter = new VaccineAddressAdapter();
        mVaccineAdapter.setData(sections);
        binding.recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        binding.recyclerView.setAdapter(mVaccineAdapter);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(), 500);
    }
}
