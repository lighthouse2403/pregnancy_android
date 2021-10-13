package com.dangthuy.trolybabau.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.dangthuy.trolybabau.MainActivity;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentProfileBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInfoFragment;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetAgeDialog;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetDateDialog;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ProfileFragment extends BaseFragment<ProfileViewModel> {
    public static final String TAG = "ProfileFragment";
    private FragmentProfileBinding binding;

    public interface IUpdateListener {
        void onUpdate();
    }

    private IUpdateListener updateListener;

    public void setUpdateListener(IUpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    private final ToolBar.OnItemToolBarClickListener onToolBarClickListener = item -> {
        switch (item) {
            case SAVE:
                if (binding.etBabyName.getText().toString() == null || binding.etBabyName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Bạn cần viết tên bé", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (viewModel.getYear() == 0) {
                    Toast.makeText(getActivity(), "Bạn cần nhập một trong ba thông số sau: ngày dự sinh, tuổi thai, kỳ kinh cuối", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.saveData(binding.etMomName.getText().toString(), binding.etBabyName.getText().toString());
                if (viewModel.isSetup()) {
                    listener.onClick();
                } else {
                    updateListener.onUpdate();
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
        viewModel.getLiveProfile().observe(this, profile -> {
            viewModel.setProfile(profile);
            updateView();
        });
        if (viewModel.isSetup()) {
            viewModel.setExpectToday();
            binding.btnExpect.setText(viewModel.getDayExpect() + " " + getString(R.string.tv_thang) + " " + (viewModel.getMonth() + 1) + ", " + viewModel.getYear());
            binding.btnAge.setText(viewModel.getWeek() + " " + getString(R.string.tv_tuan) + ((viewModel.getDay() > 0) ? (" " + viewModel.getDay() + " " + getString(R.string.tv_ngay)) : ""));
            binding.btnKyKinhCuoi.setText(viewModel.getBeginDay() + " " + getString(R.string.tv_thang) + " " + (viewModel.getBeginMonth() + 1) + ", " + viewModel.getBeginYear());
        }
    }

    private void updateView() {
        binding.etMomName.setText(viewModel.getProfile().getMomName());
        binding.etBabyName.setText(viewModel.getProfile().getBabyName());
        binding.btnExpect.setText(viewModel.getProfile().getBabyExpect());
        binding.btnAge.setText(viewModel.getProfile().getBabyAge());
        binding.btnKyKinhCuoi.setText(viewModel.getProfile().getKykinhcuoi());
    }

    private void setLayoutView(boolean isSetup) {
        binding.toolBar.setLayoutView(isSetup ? ToolBarType.SETUP : ToolBarType.BACK_SAVE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setOnClickListener() {
        binding.btnAge.setOnClickListener(view -> {
            BottomSheetAgeDialog dialog = BottomSheetAgeDialog.newInstance();
            dialog.setListener((week, day) -> {
                viewModel.setAge(week, day);
                binding.btnAge.setText(week + " " + getString(R.string.tv_tuan) + " " + day + " " + getString(R.string.tv_ngay));
                binding.btnExpect.setText(viewModel.getDayExpect() + " " + getString(R.string.tv_thang) + " " + (viewModel.getMonth() + 1) + ", " + viewModel.getYear());
                dialog.dismiss();
            });
            dialog.show(getChildFragmentManager(), BottomSheetAgeDialog.TAG);
        });
        binding.btnExpect.setOnClickListener(view -> {
            BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance(false);
            dialog.setListener((year, month, day, hour, min) -> {
                viewModel.setExpect(year, month, day);
                binding.btnExpect.setText(day + " " + getString(R.string.tv_thang) + " " + (month + 1) + ", " + year);
                binding.btnAge.setText(viewModel.getWeek() + " " + getString(R.string.tv_tuan) + ((viewModel.getDay() > 0) ? (" " + viewModel.getDay() + " " + getString(R.string.tv_ngay)) : ""));
                binding.btnKyKinhCuoi.setText(viewModel.getBeginDay() + " " + getString(R.string.tv_thang) + " " + (viewModel.getBeginMonth() + 1) + ", " + viewModel.getBeginYear());
                dialog.dismiss();
            });
            dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
        });
        binding.btnKyKinhCuoi.setOnClickListener(view -> {
            BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance(false);
            dialog.setListener((year, month, day, hour, min) -> {
                viewModel.setBegin(year, month, day);
                binding.btnKyKinhCuoi.setText(day + " " + getString(R.string.tv_thang) + " " + (month + 1) + ", " + year);
                binding.btnExpect.setText(viewModel.getDayExpect() + " " + getString(R.string.tv_thang) + " " + (viewModel.getMonth() + 1) + ", " + viewModel.getYear());
                binding.btnAge.setText(viewModel.getWeek() + " " + getString(R.string.tv_tuan) + ((viewModel.getDay() > 0) ? (" " + viewModel.getDay() + " " + getString(R.string.tv_ngay)) : ""));
                dialog.dismiss();
            });
            dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
        });
        binding.btnBabyInfo.setOnClickListener(view -> {
            if (viewModel.isSetup()) {
                ((MainActivity) requireActivity()).addFragment(BabyInfoFragment.newInstance(false));
            } else {
                addFragment(R.id.container, BabyInfoFragment.newInstance(false), BabyInfoFragment.TAG, false);
            }
        });
        binding.toolBar.setListener(onToolBarClickListener);
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
