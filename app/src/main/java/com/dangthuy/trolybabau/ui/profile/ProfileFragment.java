package com.dangthuy.trolybabau.ui.profile;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.FragmentProfileBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.main.MainFragment;
import com.dangthuy.trolybabau.ui.profile.bottom_sheet.BottomSheetAgeDialog;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ProfileFragment extends BaseFragment<ProfileViewModel> {
    private static final String TAG = "ProfileFragment";
    private FragmentProfileBinding binding;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
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

    }

    @Override
    protected void setOnClickListener() {
        binding.btnAge.setOnClickListener(view -> {
            BottomSheetAgeDialog dialog = new BottomSheetAgeDialog();
            dialog.show(getChildFragmentManager(), BottomSheetAgeDialog.TAG);
        });
        binding.btnSave.setOnClickListener(view -> viewModel.saveData());
    }

    @Override
    protected void onRefreshData() {

    }
}
