package com.dangthuy.trolybabau.common.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.dangthuy.trolybabau.databinding.DialogBabyIndexHelpBinding;

/**
 * Created by nhongthai on 5/2/2021.
 */
public class HelpIndexDialog extends BaseDialog{
    private DialogBabyIndexHelpBinding binding;

    public HelpIndexDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public View getLayoutView() {
        return binding.getRoot();
    }

    @Override
    public void initView() {
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setOnClickListener();
        setLayoutView();
    }

    private void setLayoutView() {

    }

    private void setOnClickListener() {
        binding.btnClose.setOnClickListener(view -> this.dismiss());
    }

    @Override
    public void initData() {

    }

    @Override
    public void customDialog() {
        Window window = getWindow();
        if (window != null) {
            try {
                int width = ViewGroup.LayoutParams.WRAP_CONTENT;
                int height = ViewGroup.LayoutParams.WRAP_CONTENT;
                window.setLayout(width, height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void beforeSetContentView() {
        binding = DialogBabyIndexHelpBinding.inflate(getLayoutInflater());
    }
}
