package com.dangthuy.trolybabau.ui.thaiky.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.ThaiKyDetail;
import com.dangthuy.trolybabau.databinding.ItemThaikyDetailBinding;

import java.util.List;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class ThaiKyDetailAdapter extends BaseQuickAdapter<ThaiKyDetail, BaseViewHolder> {
    public ThaiKyDetailAdapter(@Nullable List<ThaiKyDetail> data) {
        super(R.layout.item_thaiky_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThaiKyDetail item) {
        ItemThaikyDetailBinding binding = ItemThaikyDetailBinding.bind(helper.itemView);
        if (item.getTitle() != null) {
            binding.tvDescription.setText(item.getTitle());
        }
        if (item.getContent() != null) {
            binding.tvContent.setText(item.getContent());
        }
    }
}
