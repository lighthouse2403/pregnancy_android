package com.dangthuy.trolybabau.ui.baby_index.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyIndex;
import com.dangthuy.trolybabau.databinding.ItemBabyIndexDialogBinding;

import java.util.List;

/**
 * Created by nhongthai on 5/2/2021.
 */
public class BabyIndexDialogAdapter extends BaseQuickAdapter<BabyIndex, BaseViewHolder> {
    public BabyIndexDialogAdapter(@Nullable List<BabyIndex> data) {
        super(R.layout.item_baby_index_dialog, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BabyIndex item) {
        ItemBabyIndexDialogBinding binding = ItemBabyIndexDialogBinding.bind(helper.itemView);
        binding.tvAge.setText(item.getWeek());
        binding.tvBPD.setText(item.getBpdTb());
        binding.tvBPDGH.setText(item.getBpdGh());
        binding.tvFL.setText(item.getFlTb());
        binding.tvFLGH.setText(item.getFlGh());
        binding.tvEFW.setText(item.getEfwTb());
        binding.tvEFWGH.setText(item.getEfwGh());
        if (helper.getAdapterPosition() == 4) {
            binding.clContent.setBackgroundColor(mContext.getResources().getColor(R.color.green_blur));
        }
    }
}
