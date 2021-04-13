package com.dangthuy.trolybabau.ui.mom_weight.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyFoot;
import com.dangthuy.trolybabau.databinding.ItemInformationBinding;

import java.util.List;


/**
 * Created by nhongthai on 3/29/2021.
 */
public class BabyFootAdapter extends BaseQuickAdapter<BabyFoot, BaseViewHolder> {
    public BabyFootAdapter(@Nullable List<BabyFoot> data) {
        super(R.layout.item_information, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BabyFoot item) {
        ItemInformationBinding binding = ItemInformationBinding.bind(helper.itemView);
        binding.tvWeight.setText(String.valueOf(item.foot));
        binding.tvDate.setText(getTime(item));
        binding.tvAge.setText(item.createdDate);
    }

    private String getTime(BabyFoot item) {
        return ((item.hour < 10) ? "0" + item.hour : "" + item.hour) + ":" + ((item.minute < 10) ? "0" + item.minute : "" + item.minute) + ":" + ((item.second < 10) ? "0" + item.second : "" + item.second);
    }
}
