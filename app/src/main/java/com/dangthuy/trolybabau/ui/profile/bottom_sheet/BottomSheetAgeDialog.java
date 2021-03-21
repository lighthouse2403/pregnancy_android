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

    public static BottomSheetAgeDialog newInstance() {
        BottomSheetAgeDialog fragment = new BottomSheetAgeDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public interface IBottomSheetListener {
        void onClick(int week, int day);
    }

    private IBottomSheetListener listener;

    public void setListener(IBottomSheetListener listener) {
        this.listener = listener;
    }

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
        binding.npWeek.setOnValueChangedListener((numberPicker, i, i1) -> binding.npWeek.setValue(i1));
        binding.npDay.setOnValueChangedListener((picker, oldVal, newVal) -> binding.npDay.setValue(newVal));
    }

    private void setOnClickListener() {
        binding.btnClose.setOnClickListener(view -> this.dismiss());
        binding.btnSave.setOnClickListener(view -> listener.onClick(binding.npWeek.getValue(), binding.npDay.getValue()));
    }
}
