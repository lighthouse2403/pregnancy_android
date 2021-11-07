package com.dangthuy.trolybabau.ui.baby_index;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.dialog.BabyIndexDialog;
import com.dangthuy.trolybabau.common.dialog.HelpIndexDialog;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentBabyIndexBinding;
import com.dangthuy.trolybabau.ui.baby_index.adapter.BabyIndexAdapter;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInfoFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class BabyIndexFragment extends BaseFragment<BabyIndexViewModel> {
    public static final String TAG = "BabyIndexFragment";
    private FragmentBabyIndexBinding binding;
    private BabyIndexAdapter mBabyIndexAdapter;
    private ToolBar.OnItemToolBarClickListener toolbarListener = item -> {
        switch (item) {
            case ADD:
                addFragment(R.id.container, BabyInfoFragment.newInstance(false), BabyInfoFragment.TAG, false);
                break;
            case TITLE:
                showHelp();
                break;
        }
    };

    public static BabyIndexFragment newInstance() {
        BabyIndexFragment fragment = new BabyIndexFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyIndexViewModel> provideViewModelClass() {
        return BabyIndexViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_baby_index;
    }

    @Override
    protected void initView() {
        binding = (FragmentBabyIndexBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        viewModel.getIndexLiveData().observe(this, babyIndices -> {
            mBabyIndexAdapter.setNewData(babyIndices);
        });
        initAdapter();
    }

    private void initAdapter() {
        mBabyIndexAdapter = new BabyIndexAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mBabyIndexAdapter);
        mBabyIndexAdapter.setListener(this::showDialog);
    }

    private void showDialog(int week) {
        if (getContext() != null) {
            BabyIndexDialog dialog = new BabyIndexDialog(getContext());
            dialog.setmData(viewModel.getData(week));
            dialog.show();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.ADD);
        binding.ltTitle.tvBPD.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
        binding.ltTitle.tvEFW.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
        binding.ltTitle.tvFL.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
        binding.ltTitle.tvAge.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(toolbarListener);
        binding.ltTitle.clContent.setOnClickListener(view -> showHelp());
    }

    private void showHelp() {
        if (getContext() != null) {
            HelpIndexDialog dialog = new HelpIndexDialog(getContext());
            dialog.show();
        }
    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchData();
    }
}
