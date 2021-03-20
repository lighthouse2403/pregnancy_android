package com.dangthuy.trolybabau.common.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.databinding.DialogLoadingBinding;


public class LoadingDialog extends BaseDialog {
    private DialogLoadingBinding binding;

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public View getLayoutView() {
        return binding.getRoot();
    }

    @Override
    public void initView() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
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
        binding = DialogLoadingBinding.inflate(getLayoutInflater());
    }
}
