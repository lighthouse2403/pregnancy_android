package com.dangthuy.trolybabau.common.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.databinding.DialogBabyIndexBinding;
import com.dangthuy.trolybabau.ui.baby_index.adapter.BabyIndexAdapter;
import com.dangthuy.trolybabau.ui.baby_index.adapter.BabyIndexDialogAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class BabyIndexDialog extends BaseDialog{
    private DialogBabyIndexBinding binding;
    private BabyIndexDialogAdapter mBabyIndexAdapter;
    private List<BabyIndex> mData;

    public void setmData(List<BabyIndex> mData) {
        this.mData = mData;
    }

    public BabyIndexDialog(@NonNull Context context) {
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
        initAdapter();
        setLayoutView();
    }

    private void initAdapter() {
        mBabyIndexAdapter = new BabyIndexDialogAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mBabyIndexAdapter);
    }

    private void setLayoutView() {
        mBabyIndexAdapter.setNewData(mData);
    }

    private void setOnClickListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void customDialog() {
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.background_dialog_baby_index);
            try {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.WRAP_CONTENT;
                window.setLayout(width, height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void beforeSetContentView() {
        binding = DialogBabyIndexBinding.inflate(getLayoutInflater());
    }
}
