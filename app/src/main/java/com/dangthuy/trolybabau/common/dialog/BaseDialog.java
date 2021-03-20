package com.dangthuy.trolybabau.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.R;

public abstract class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        beforeSetContentView();
        setContentView(getLayoutView());
        customDialog();
        initView();
        initData();
    }

    public abstract View getLayoutView();

    public abstract void initView();

    public abstract void initData();

    public abstract void customDialog();

    protected abstract void beforeSetContentView();

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
