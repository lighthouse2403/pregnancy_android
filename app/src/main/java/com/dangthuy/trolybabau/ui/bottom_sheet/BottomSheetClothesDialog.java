package com.dangthuy.trolybabau.ui.bottom_sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.BottomSheetClothesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class BottomSheetClothesDialog extends BottomSheetDialogFragment {
    public static final String TAG = "BottomSheetClothesDialo";
    private BottomSheetClothesBinding binding;
    public static final int MANAGE = 0;
    public static final int HOSPITAL = 1;

    public interface IBottomSheetListener {
        void onClickItem(int position);
    }

    private IBottomSheetListener listener;

    public void setListener(IBottomSheetListener listener) {
        this.listener = listener;
    }

    public static BottomSheetClothesDialog newInstance() {
        BottomSheetClothesDialog fragment = new BottomSheetClothesDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_clothes, container, false);
        setOnClickListener();
        return binding.getRoot();
    }

    private void setOnClickListener() {
        binding.btnManage.setOnClickListener(view -> listener.onClickItem(MANAGE));
        binding.btnClose.setOnClickListener(view -> this.dismiss());
        binding.btnHospital.setOnClickListener(view -> listener.onClickItem(HOSPITAL));
    }
}
