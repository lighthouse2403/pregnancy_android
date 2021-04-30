package com.dangthuy.trolybabau.common.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.DiaglogAlarmBinding;

/**
 * Created by nhongthai on 4/30/2021.
 */
public class AlarmDialog extends BaseDialog{
    private DiaglogAlarmBinding binding;
    private String textAlarm;
    public AlarmDialog(@NonNull Context context) {
        super(context);
    }

    public interface IClickListener {
        void onClick();
    }

    private IClickListener listener;

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View getLayoutView() {
        return binding.getRoot();
    }

    @Override
    public void initView() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setOnClickListener();
        setLayoutView();
    }

    private void setLayoutView() {
        binding.tvContent.setText(textAlarm);
    }

    private void setOnClickListener() {
        binding.btnClose.setOnClickListener(view -> listener.onClick());
    }

    @Override
    public void initData() {

    }

    @Override
    public void customDialog() {
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.corner_dialog);
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
        binding = DiaglogAlarmBinding.inflate(getLayoutInflater());
    }

    public void setTextAlarm(String textAlarm) {
        this.textAlarm = textAlarm;
    }
}
