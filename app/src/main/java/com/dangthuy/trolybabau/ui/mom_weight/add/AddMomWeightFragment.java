package com.dangthuy.trolybabau.ui.mom_weight.add;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentMomWeightAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetDateDialog;
import com.dangthuy.trolybabau.ui.home.HomeViewModel;
import com.dangthuy.trolybabau.ui.mom_weight.InfomartionViewModel;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class AddMomWeightFragment extends BaseFragment<InfomartionViewModel> {
    public static final String TAG = "AddMomWeightFragment";
    private FragmentMomWeightAddBinding binding;

    public static AddMomWeightFragment newInstance() {
        AddMomWeightFragment fragment = new AddMomWeightFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<InfomartionViewModel> provideViewModelClass() {
        return InfomartionViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mom_weight_add;
    }

    @Override
    protected void initView() {
        binding = (FragmentMomWeightAddBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(HomeViewModel.CAN_NANG_CUA_ME);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        binding.tvDateValue.setOnClickListener(view -> {
            BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance();
            dialog.setListener((year, month, day) -> {
                viewModel.setDate(year, month, day);
                binding.tvDateValue.setText(day + " " + getString(R.string.tv_thang) + " " + month + ", " + year);
                dialog.dismiss();
            });
            dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
        });
    }

    @Override
    protected void onRefreshData() {

    }
}
