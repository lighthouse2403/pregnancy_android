package com.dangthuy.trolybabau.ui.profile.bottom_sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.BottomSheetProfileAgeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class BottomSheetAgeDialog extends BottomSheetDialogFragment {
    public static final String TAG = "BottomSheetAgeDialog";

    private BottomSheetProfileAgeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_profile_age, container, false);
        initPicker();
        setOnClickListener();
        return binding.getRoot();
    }

    private void initPicker() {
        binding.npWeek.setMaxValue(50);
        binding.npWeek.setMinValue(0);
        binding.npWeek.setValue(25);
        binding.npWeek.setOnValueChangedListener((numberPicker, i, i1) -> {
            binding.npWeek.setValue(i1);

        });
    }

    private void setOnClickListener() {

    }
}
