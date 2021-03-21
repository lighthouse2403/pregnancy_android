package com.dangthuy.trolybabau.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentProfileBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInfoFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInforViewModel;
import com.dangthuy.trolybabau.ui.main.MainFragment;
import com.dangthuy.trolybabau.ui.profile.bottom_sheet.BottomSheetAgeDialog;
import com.dangthuy.trolybabau.ui.profile.bottom_sheet.BottomSheetDateDialog;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ProfileFragment extends BaseFragment<ProfileViewModel> {
    public static final String TAG = "ProfileFragment";
    private FragmentProfileBinding binding;
    private ToolBar.OnItemToolBarClickListener onToolBarClickListener = item -> {
        switch (item) {
            case SAVE:
                viewModel.saveData();
                if (viewModel.isSetup()) {
                    listener.onClick();
                } else {
                    getParentFragmentManager().popBackStack();
                }
                break;
            case BACK:
                getParentFragmentManager().popBackStack();
                break;
        }
    };

    public static ProfileFragment newInstance(boolean isSetup) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putBoolean(TAG, isSetup);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<ProfileViewModel> provideViewModelClass() {
        return ProfileViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        binding = (FragmentProfileBinding) getBinding();
        if (getArguments() != null) {
            viewModel.setIsSetup(getArguments().getBoolean(TAG));
            setLayoutView(viewModel.isSetup());
        }
    }

    private void setLayoutView(boolean isSetup) {
        binding.toolBar.setLayoutView(isSetup ? ToolBarType.SETUP : ToolBarType.EXPECT);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setOnClickListener() {
        binding.btnAge.setOnClickListener(view -> {
            BottomSheetAgeDialog dialog = BottomSheetAgeDialog.newInstance();
            dialog.setListener((week, day) -> {
                viewModel.setAge(week, day);
                binding.btnAge.setText(week + " " + getString(R.string.tv_tuan) + " " + day + " " + getString(R.string.tv_ngay));
                dialog.dismiss();
            });
            dialog.show(getChildFragmentManager(), BottomSheetAgeDialog.TAG);
        });
        binding.btnExpect.setOnClickListener(view -> {
            BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance();
            dialog.setListener((year, month, day) -> {
                viewModel.setExpect(year, month, day);
                binding.btnExpect.setText(day + " " + getString(R.string.tv_thang) + " " + month + ", " + year);
                dialog.dismiss();
            });
            dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
        });
        binding.btnKyKinhCuoi.setOnClickListener(view -> {
            BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance();
            dialog.setListener((year, month, day) -> {
                viewModel.setExpect(year, month, day);
                binding.btnKyKinhCuoi.setText(day + " " + getString(R.string.tv_thang) + " " + month + ", " + year);
                dialog.dismiss();
            });
            dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
        });
        binding.btnBabyInfo.setOnClickListener(view -> {
            addFragment(R.id.container, BabyInfoFragment.newInstance(false), BabyInfoFragment.TAG, false);
        });
        binding.toolBar.setListener(onToolBarClickListener);
    }

    @Override
    protected void onRefreshData() {

    }
}
