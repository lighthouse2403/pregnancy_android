package com.dangthuy.trolybabau.ui.diary.add;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.BetweenSpacesItemDecoration;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Diary;
import com.dangthuy.trolybabau.databinding.FragmentDiaryAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.diary.DiaryFragment;
import com.dangthuy.trolybabau.ui.diary.DiaryViewModel;
import com.dangthuy.trolybabau.ui.diary.adapter.ImageAdapter;
import com.dangthuy.trolybabau.ui.mom_weight.add.AddBabyFootFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class AddDiaryFragment extends BaseFragment<DiaryViewModel> {
    public static final String TAG = "AddDiaryFragment";
    private FragmentDiaryAddBinding binding;
    private ImageAdapter mImageAdapter;

    public interface IAddListener {
        void onSaved();
    }

    private IAddListener addListener;

    public void setAddListener(IAddListener listener) {
        this.addListener = listener;
    }

    private final ToolBar.OnItemToolBarClickListener toolbarListener = item -> {
        switch (item) {
            case BACK:
                getParentFragmentManager().popBackStack();
                break;
            case CAMERA:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 1);
                break;
        }
    };

    public static AddDiaryFragment newInstance() {
        AddDiaryFragment fragment = new AddDiaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<DiaryViewModel> provideViewModelClass() {
        return DiaryViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_diary_add;
    }

    @Override
    protected void initView() {
        binding = (FragmentDiaryAddBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        initAdapter();
        viewModel.getImages().observe(this, strings -> {
            loadingDialog.dismiss();
            mImageAdapter.setNewData(strings);
        });
    }

    private void initAdapter() {
        mImageAdapter = new ImageAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.setAdapter(mImageAdapter);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DIARY_ADD);
        binding.toolBar.setTitle(DateUtils.formatDate(new Date()));
    }

    @Override
    protected void setOnClickListener() {
        binding.btnSave.setOnClickListener(view -> {
            if (!binding.etTitle.getText().toString().isEmpty() && !binding.etContent.getText().toString().isEmpty()) {
                viewModel.saveDiaryToDb(binding.etTitle.getText().toString(), binding.etContent.getText().toString());
                addListener.onSaved();
                getParentFragmentManager().popBackStack();
            } else {
                showToast(getString(R.string.tv_thong_bao_nhap_noi_dung));
            }
        });
        binding.toolBar.setListener(toolbarListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            loadingDialog.show();
            new Handler().postDelayed(() -> viewModel.fetchImages(data), 500);
        }
    }

    @Override
    protected void onRefreshData() {

    }
}
