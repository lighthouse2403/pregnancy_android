package com.dangthuy.trolybabau.ui.alarm.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.Alarm;
import com.dangthuy.trolybabau.databinding.ItemAlarmBinding;

import java.util.List;

/**
 * Created by nhongthai on 4/30/2021.
 */
public class AlarmAdapter extends BaseQuickAdapter<Alarm, BaseViewHolder> {
    public AlarmAdapter(@Nullable List<Alarm> data) {
        super(R.layout.item_alarm, data);
    }

    public interface IAlarmListener {
        void onClickItem(Alarm item);

        void onSwitch(Alarm item, int position);
    }

    private IAlarmListener alarmListener;

    public void setAlarmListener(IAlarmListener alarmListener) {
        this.alarmListener = alarmListener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, Alarm item) {
        ItemAlarmBinding binding = ItemAlarmBinding.bind(helper.itemView);
        binding.tvDate.setText(item.getDayOfMonth() + " " + mContext.getResources().getString(R.string.tv_thang) + " " + item.getMonth() + ", " + item.getYear());
        binding.tvTime.setText((item.getHour() < 10 ? "0" + item.getHour() : item.getHour()) + ":" + (item.getMin() < 10 ? "0" + item.getMin() : item.getMin()));
        binding.tvNote.setText(item.getNote());
        binding.switchOnOff.setChecked(item.isStatus());
        binding.clContent.setOnClickListener(view -> alarmListener.onClickItem(item));
        binding.switchOnOff.setOnCheckedChangeListener((compoundButton, b) -> alarmListener.onSwitch(item, helper.getAdapterPosition()));
    }


}
