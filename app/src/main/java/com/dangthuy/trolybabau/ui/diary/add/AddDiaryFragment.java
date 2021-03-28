package com.dangthuy.trolybabau.ui.diary.add;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Diary;
import com.dangthuy.trolybabau.databinding.FragmentDiaryAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.diary.DiaryFragment;
import com.dangthuy.trolybabau.ui.diary.DiaryViewModel;

import java.util.Date;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class AddDiaryFragment extends BaseFragment<DiaryViewModel> {
    public static final String TAG = "AddDiaryFragment";
    private FragmentDiaryAddBinding binding;
    private final ToolBar.OnItemToolBarClickListener toolbarListener = item -> {
        switch (item) {
            case BACK:
                getParentFragmentManager().popBackStack();;
                break;
            case CAMERA:

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
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DIARY_ADD);
        binding.toolBar.setTitle(DateUtils.formatDate(new Date()));
    }

    @Override
    protected void setOnClickListener() {
        binding.btnSave.setOnClickListener(view -> {});
        binding.toolBar.setListener(toolbarListener);
    }

    @Override
    protected void onRefreshData() {

    }
}
