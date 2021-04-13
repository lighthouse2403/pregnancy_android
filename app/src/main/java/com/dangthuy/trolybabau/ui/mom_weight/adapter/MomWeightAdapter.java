package com.dangthuy.trolybabau.ui.mom_weight.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.MomWeight;
import com.dangthuy.trolybabau.databinding.ItemInformationBinding;

import java.util.List;

/**
 * Created by nhongthai on 4/13/2021.
 */
public class MomWeightAdapter extends BaseQuickAdapter<MomWeight, BaseViewHolder> {
    public MomWeightAdapter(@Nullable List<MomWeight> data) {
        super(R.layout.item_information, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MomWeight item) {
        ItemInformationBinding binding = ItemInformationBinding.bind(helper.itemView);
        binding.tvWeight.setText(item.weight);
        binding.tvDate.setText(item.createdDate);
        binding.tvAge.setText(item.week + " tuần " + item.dayOfWeek + " ngày");
    }
}
