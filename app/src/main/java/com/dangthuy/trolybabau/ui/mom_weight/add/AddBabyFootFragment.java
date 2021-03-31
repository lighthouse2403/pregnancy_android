package com.dangthuy.trolybabau.ui.mom_weight.add;

import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentBabyFootAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.mom_weight.InfomartionViewModel;

import java.util.Date;

/**
 * Created by nhongthai on 3/31/2021.
 */
public class AddBabyFootFragment extends BaseFragment<InfomartionViewModel> {
    public static final String TAG = "AddBabyFootFragment";
    private FragmentBabyFootAddBinding binding;

    public static AddBabyFootFragment newInstance() {
        AddBabyFootFragment fragment = new AddBabyFootFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<InfomartionViewModel> provideViewModelClass() {
        return InfomartionViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_baby_foot_add;
    }

    @Override
    protected void initView() {
        binding = (FragmentBabyFootAddBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(DateUtils.formatDate(new Date()));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {

    }
}
