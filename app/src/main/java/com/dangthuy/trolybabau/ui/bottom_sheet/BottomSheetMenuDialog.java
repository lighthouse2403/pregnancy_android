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
public class BottomSheetMenuDialog extends BottomSheetDialogFragment {
    public static final String TAG = "BottomSheetClothesDialo";
    private BottomSheetClothesBinding binding;
    public static final int MENU_ONE = 0;
    public static final int MENU_TWO = 1;
    public static final String CLOTHES = "clothes";
    public static final String BABY_INDEX = "baby_index";

    public interface IBottomSheetListener {
        void onClickItem(int position);
    }

    private IBottomSheetListener listener;

    public void setListener(IBottomSheetListener listener) {
        this.listener = listener;
    }

    public static BottomSheetMenuDialog newInstance(String typeMenu) {
        BottomSheetMenuDialog fragment = new BottomSheetMenuDialog();
        Bundle args = new Bundle();
        args.putString(TAG, typeMenu);
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
        if (getArguments() != null) {
            if (getArguments().getString(TAG) != null) {
                setLayoutView(getArguments().getString(TAG));
            }
        }
        setOnClickListener();
        return binding.getRoot();
    }

    private void setLayoutView(String typeMenu) {
        switch (typeMenu) {
            case CLOTHES:
                binding.btnMenuOne.setText(getString(R.string.tv_quan_ly_do_so_sinh));
                binding.btnMenuTwo.setText(getString(R.string.tv_mang_vao_vien));
                break;
            case BABY_INDEX:
                binding.btnMenuOne.setText(getString(R.string.tv_may_anh));
                binding.btnMenuTwo.setText(getString(R.string.tv_thu_vien_anh));
                break;
        }
    }

    private void setOnClickListener() {
        binding.btnMenuOne.setOnClickListener(view -> listener.onClickItem(MENU_ONE));
        binding.btnMenuTwo.setOnClickListener(view -> listener.onClickItem(MENU_TWO));
        binding.btnClose.setOnClickListener(view -> this.dismiss());
    }
}
