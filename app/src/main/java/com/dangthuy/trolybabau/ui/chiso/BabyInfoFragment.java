package com.dangthuy.trolybabau.ui.chiso;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentBabyInfomationBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.home.HomeFragment;

/**
 * Created by nhongthai on 21/03/2021.
 */
public class BabyInfoFragment extends BaseFragment<BabyInforViewModel> {
    public static final String TAG = "BabyInfoFragment";
    private FragmentBabyInfomationBinding binding;
    private ToolBar.OnItemToolBarClickListener onToolBarClickListener = item -> {
        switch (item) {
            case ADD:

                break;
            case BACK:
                getParentFragmentManager().popBackStack();
                break;
        }
    };

    public static BabyInfoFragment newInstance(boolean isTab) {
        BabyInfoFragment fragment = new BabyInfoFragment();
        Bundle args = new Bundle();
        args.putBoolean(TAG, isTab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyInforViewModel> provideViewModelClass() {
        return BabyInforViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_baby_infomation;
    }

    @Override
    protected void initView() {
        binding = (FragmentBabyInfomationBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView(getArguments().getBoolean(TAG));
        }
    }

    private void setLayoutView(boolean isTab) {
        binding.toolBar.setLayoutView(isTab ? ToolBarType.BABY_INFO_TAB : ToolBarType.BABY_INFO);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(onToolBarClickListener);
    }

    @Override
    protected void onRefreshData() {

    }
}
