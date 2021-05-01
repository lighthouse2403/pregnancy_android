package com.dangthuy.trolybabau.ui.baby_index.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.databinding.ItemBabyIndexBinding;

import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class BabyIndexAdapter extends BaseQuickAdapter<BabyIndex, BaseViewHolder> {
    public BabyIndexAdapter(@Nullable List<BabyIndex> data) {
        super(R.layout.item_baby_index, data);
    }

    public interface IClickItemListener {
        void onClick(int week);
    }

    private IClickItemListener listener;

    public void setListener(IClickItemListener listener) {
        this.listener = listener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, BabyIndex item) {
        ItemBabyIndexBinding binding = DataBindingUtil.bind(helper.itemView);
        int week = Integer.parseInt(item.getWeek().substring(0,item.getWeek().indexOf("+")));
        binding.tvAge.setText(mContext.getResources().getString(R.string.tv_tuan) + " \n" + week);
        binding.tvBPD.setText(item.getBpdTb());
        binding.tvFL.setText(item.getFlTb());
        if (week < 21) {
            binding.tvEFW.setText(item.getEfwGh());
        } else {
            binding.tvEFW.setText(item.getEfwTb());
        }
        if (week > 15) {
            binding.clContent.setOnClickListener(view -> listener.onClick(week));
        }
    }
}
