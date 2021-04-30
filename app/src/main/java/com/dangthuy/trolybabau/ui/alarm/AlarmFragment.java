package com.dangthuy.trolybabau.ui.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Alarm;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.alarm.adapter.AlarmAdapter;
import com.dangthuy.trolybabau.ui.alarm.detail.DetailAlarmFragment;
import com.dangthuy.trolybabau.ui.alarm.receiver.AlarmReceiver;
import com.dangthuy.trolybabau.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 4/26/2021.
 */
public class AlarmFragment extends BaseFragment<AlarmViewModel> {
    public static final String TAG = "AlarmFragment";
    private FragmentCommonBinding binding;
    private AlarmAdapter mAlarmAdapter;
    private final AlarmAdapter.IAlarmListener alarmListener = new AlarmAdapter.IAlarmListener() {
        @Override
        public void onClickItem(Alarm item) {
            gotoDetail(item);
        }

        @Override
        public void onSwitch(Alarm item, int position) {
            viewModel.switchOnOff(item);
            new Handler().postDelayed(() -> mAlarmAdapter.notifyItemChanged(position), 100);
            startAlarm();
        }
    };
    private final ToolBar.OnItemToolBarClickListener toolbarListener = item -> {
        switch (item) {
            case BACK:
                getParentFragmentManager().popBackStack();
                break;
            case ADD:
                gotoDetail(null);
                break;
        }
    };

    private void gotoDetail(Alarm item) {
        DetailAlarmFragment fragment = DetailAlarmFragment.newInstance(item);
        fragment.setSaveListener(() -> {
            this.onRefreshData();
            viewModel.setAlarm(item);
            startAlarm();
        });
        addFragment(R.id.container, fragment, DetailAlarmFragment.TAG, false);
    }

    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<AlarmViewModel> provideViewModelClass() {
        return AlarmViewModel.class;
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
        viewModel.getAlarms().observe(this, alarms -> {
            loadingDialog.dismiss();
            mAlarmAdapter.setNewData(alarms);
        });
    }

    private void initAdapter() {
        mAlarmAdapter = new AlarmAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mAlarmAdapter);
        mAlarmAdapter.setAlarmListener(alarmListener);
    }

    private void startAlarm() {
        AlarmManager alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        intent.setAction(Constants.ALARM_ACTION);
        intent.putExtra(Constants.ALARM_DATA, viewModel.getNote());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
        if (viewModel.isCheck()) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5 * 1000L, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.BACK_ADD);
        binding.toolBar.setTitle(getString(R.string.tv_nhac_nho));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(toolbarListener);
    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(), 300);
    }
}
