package com.dangthuy.trolybabau.ui.chiso.add;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.BabyInfo;
import com.dangthuy.trolybabau.databinding.FragmentBabyInfoAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetMenuDialog;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetDateDialog;
import com.dangthuy.trolybabau.ui.chiso.BabyInforViewModel;
import com.dangthuy.trolybabau.ui.clothes.hospital.HospitalClothesFragment;
import com.dangthuy.trolybabau.ui.diary.add.AddDiaryFragment;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nhongthai on 5/2/2021.
 */
public class AddBabyInfoFragment extends BaseFragment<BabyInforViewModel> {
    public static final String TAG = "AddBabyInfoFragment";
    private FragmentBabyInfoAddBinding binding;

    public static AddBabyInfoFragment newInstance() {
        AddBabyInfoFragment fragment = new AddBabyInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public interface IAddListener {
        void onSaved();
    }

    private IAddListener saveListener;

    private IAddListener addListener;

    public void setAddListener(IAddListener listener) {
        this.addListener = listener;
    }

    @Override
    protected Class<BabyInforViewModel> provideViewModelClass() {
        return BabyInforViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_baby_info_add;
    }

    @Override
    protected void initView() {
        binding = (FragmentBabyInfoAddBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        viewModel.getImages().observe(this, strings -> {
            loadingDialog.dismiss();
            if (strings != null && strings.size() > 0) {
                Uri uri = Uri.parse(strings.get(0));
                Glide.with(this).load(uri)
                        .error(R.drawable.new_picture)
                        .into(binding.ivImage);
            }
        });
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_them_thong_tin_thai));
        binding.tvDate.setText(viewModel.getDate());
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        binding.tvDate.setOnClickListener(view -> showDateDialog());
        binding.ivImage.setOnClickListener(view -> showMenu());
        binding.btnSave.setOnClickListener(view -> {
            if (!binding.etWeight.getText().toString().isEmpty()) {
                viewModel.saveBabyInfoToDb(binding.etWeight.getText().toString());
                addListener.onSaved();
                getParentFragmentManager().popBackStack();
            } else {
                showToast(getString(R.string.tv_ban_can_nhap_can_nang));
            }
        });
    }

    private void showMenu() {
        BottomSheetMenuDialog dialog = BottomSheetMenuDialog.newInstance(BottomSheetMenuDialog.BABY_INDEX);
        dialog.setListener(position -> {
            if (position == BottomSheetMenuDialog.MENU_ONE) {

            } else {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 1);
            }
            dialog.dismiss();
        });
        dialog.show(getChildFragmentManager(), BottomSheetMenuDialog.TAG);
    }

    @SuppressLint("SetTextI18n")
    private void showDateDialog() {
        BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance(true);
        dialog.setListener((year, month, day, hour, minute) -> {
            viewModel.setDate(year, month, day, hour, minute);
            binding.tvDate.setText(day + " " + getString(R.string.tv_thang) + " " + month + ", " + year);
            dialog.dismiss();
        });
        dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
    }

    @Override
    protected void onRefreshData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            loadingDialog.show();
            new Handler().postDelayed(() -> viewModel.fetchImages(data), 500);
        }
    }
}
