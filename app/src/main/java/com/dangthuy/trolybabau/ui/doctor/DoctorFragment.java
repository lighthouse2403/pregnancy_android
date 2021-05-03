package com.dangthuy.trolybabau.ui.doctor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.MainActivity;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetMenuDialog;
import com.dangthuy.trolybabau.ui.diary.DiaryFragment;
import com.dangthuy.trolybabau.ui.doctor.adapter.DoctorAdapter;

import java.util.ArrayList;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class DoctorFragment extends BaseFragment<DoctorViewModel> {
    public static final String TAG = "DoctorFragment";
    private FragmentCommonBinding binding;
    private DoctorAdapter mDoctorAdapter;

    public static DoctorFragment newInstance() {
        DoctorFragment fragment = new DoctorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<DoctorViewModel> provideViewModelClass() {
        return DoctorViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentCommonBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        initAdapter();
        viewModel.getDoctorLiveData().observe(this, doctors -> {
            loadingDialog.dismiss();
            mDoctorAdapter.setNewData(doctors);
        });
    }

    private void initAdapter() {
        mDoctorAdapter = new DoctorAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mDoctorAdapter);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_bac_si));
        binding.search.setVisibility(View.VISIBLE);
        binding.search.setQueryHint(getString(R.string.tv_tim_kiem_bac_si));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && newText.length() > 0) {
                    viewModel.searchByName(newText);
                }
                return true;
            }
        });
        mDoctorAdapter.setListener((doctor, action) -> {
            if (action == DoctorAdapter.ACTION_CALL) {
                viewModel.setDoctor(doctor);
                BottomSheetMenuDialog dialog = BottomSheetMenuDialog.newInstance(BottomSheetMenuDialog.DOCTOR);
                dialog.setText(viewModel.getPhone());
                dialog.setListener(position -> {
                    ((MainActivity) getActivity()).setPermissionListener(isAccepted -> {
                        if (isAccepted) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + viewModel.getPhone()));
                            startActivity(intent);
                        }
                    });
                    ((MainActivity) getActivity()).requestPermission(1);
                    dialog.dismiss();
                });
                dialog.show(getChildFragmentManager(), BottomSheetMenuDialog.TAG);
            }
        });
    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(), 300);
    }

}
