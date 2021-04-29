package com.dangthuy.trolybabau.ui.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentAlarmBinding;
import com.dangthuy.trolybabau.ui.alarm.receiver.AlarmReceiver;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.vaccine.VaccineAddressFragment;

/**
 * Created by nhongthai on 4/26/2021.
 */
public class AlarmFragment extends BaseFragment<AlarmViewModel> {
    public static final String TAG = "AlarmFragment";
    private FragmentAlarmBinding binding;

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
        return R.layout.fragment_alarm;
    }

    @Override
    protected void initView() {
        binding = (FragmentAlarmBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        test();
    }

    private void test() {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        intent.setAction("dangthuy_alarm");
        intent.putExtra("data","hello");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        int ALARM_DELAY_IN_SECOND = 5;
        long alarmTimeAtUTC = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND * 1_000L;
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, pendingIntent);
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_nhac_nho));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {

    }
}
