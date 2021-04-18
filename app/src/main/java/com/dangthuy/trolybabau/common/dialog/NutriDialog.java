package com.dangthuy.trolybabau.common.dialog;

import android.content.Context;
import android.net.http.SslError;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.databinding.DialogNutriBinding;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class NutriDialog extends BaseDialog {
    private static final String TAG = "NutriDialog";
    private DialogNutriBinding binding;
    private Nutri mNutri;

    public NutriDialog(@NonNull Context context) {
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
        setOnClickListener();
        setLayoutView();
    }

    private void setLayoutView() {
        binding.tvTitle.setText(mNutri.getName());
//        binding.tvContent.setText(mNutri.getFullDescription());

        binding.tvContent.loadDataWithBaseURL(null, mNutri.getFullDescription(), "text/html; charset=UTF-8", "utf-8", null);
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
        binding = DialogNutriBinding.inflate(getLayoutInflater());
    }

    public void setmNutri(Nutri mNutri) {
        this.mNutri = mNutri;
    }
}
