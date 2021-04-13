package com.dangthuy.trolybabau.ui.bottom_sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.BottomSheetProfileExpectBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

/**
 * Created by nhongthai on 21/03/2021.
 */
public class BottomSheetDateDialog extends BottomSheetDialogFragment {
    public static final String TAG = "BottomSheetDateDialog";
    private BottomSheetProfileExpectBinding binding;

    public interface IBottomSheetListener {
        void onClick(int year, int month, int day, int hour, int minute);
    }

    private IBottomSheetListener listener;

    public void setListener(IBottomSheetListener listener) {
        this.listener = listener;
    }

    public static BottomSheetDateDialog newInstance(boolean isShowTimePicker) {
        BottomSheetDateDialog fragment = new BottomSheetDateDialog();
        Bundle args = new Bundle();
        args.putBoolean(TAG, isShowTimePicker);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_profile_expect, container, false);
        if (getArguments() != null) {
            if (getArguments().getBoolean(TAG)) {
                binding.clock.setVisibility(View.VISIBLE);
                binding.clock.setIs24HourView(true);
            }
        }
        initDate(Calendar.getInstance());
        setOnClickListener();
        return binding.getRoot();
    }

    private void setOnClickListener() {
        binding.btnClose.setOnClickListener(view -> this.dismiss());
        binding.btnToday.setOnClickListener(view -> initDate(Calendar.getInstance()));
        binding.btnSave.setOnClickListener(view -> listener.onClick(binding.calendar.getYear(), binding.calendar.getMonth() + 1, binding.calendar.getDayOfMonth(), binding.clock.getHour(), binding.clock.getMinute()));
    }

    private void initDate(Calendar calendar) {
        binding.calendar.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), (datePicker, i, i1, i2) -> {

        });
    }
}
