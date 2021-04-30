package com.dangthuy.trolybabau.ui.alarm.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Alarm;
import com.dangthuy.trolybabau.databinding.FragmentAlarmBinding;
import com.dangthuy.trolybabau.ui.alarm.AlarmFragment;
import com.dangthuy.trolybabau.ui.alarm.AlarmViewModel;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetDateDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class DetailAlarmFragment extends BaseFragment<AlarmViewModel> {
    public static final String TAG = "DetailAlarmFragment";
    private FragmentAlarmBinding binding;

    public interface ISaveListener {
        void onSave();
    }

    private ISaveListener saveListener;

    public void setSaveListener(ISaveListener saveListener) {
        this.saveListener = saveListener;
    }

    private final ToolBar.OnItemToolBarClickListener toolBarListener = item -> {
        switch (item) {
            case BACK:
                getParentFragmentManager().popBackStack();
                break;
            case SAVE:
                viewModel.saveAlarm(binding.etNote.getText().toString(), binding.switchOnOff.isChecked());
                saveListener.onSave();
                getParentFragmentManager().popBackStack();
                break;
        }
    };

    public static DetailAlarmFragment newInstance(Alarm item) {
        DetailAlarmFragment fragment = new DetailAlarmFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<AlarmViewModel> provideViewModelClass() {
        return AlarmViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_alarm;
    }

    @Override
    protected void initView() {
        binding = (FragmentAlarmBinding) getBinding();
        if (getArguments() != null) {
            viewModel.setAlarm(getArguments().getParcelable(TAG));
            setLayoutView(viewModel.isAdd());
        }
    }

    private void setLayoutView(boolean isAdd) {
        binding.toolBar.setLayoutView(ToolBarType.BACK_SAVE);
        binding.toolBar.setTitle(isAdd ? getString(R.string.tv_them_ke_hoach) : getString(R.string.tv_sua_ke_hoach));
        binding.btnDate.setText(viewModel.getDate());
        binding.btnTime.setText(viewModel.getTime());
        binding.switchOnOff.setChecked(viewModel.isCheck());
        binding.etNote.setText(viewModel.getNote());
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(toolBarListener);
        binding.btnDate.setOnClickListener(this::showBottomsheet);
        binding.btnTime.setOnClickListener(this::showBottomsheet);
    }

    @SuppressLint("SetTextI18n")
    private void showBottomsheet(View view) {
        BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance(true);
        dialog.setListener((year, month, day, hour, min) -> {
            viewModel.setAlarm(year, month, day, hour, min);
            binding.btnDate.setText(day + " " + getString(R.string.tv_thang) + " " + month + ", " + year);
            binding.btnTime.setText((hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min));
            dialog.dismiss();
        });
        dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
    }

    @Override
    protected void onRefreshData() {

    }
}
