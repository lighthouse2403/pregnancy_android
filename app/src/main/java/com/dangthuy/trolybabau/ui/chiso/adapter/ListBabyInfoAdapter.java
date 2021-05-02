package com.dangthuy.trolybabau.ui.chiso.adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.BabyInfo;
import com.dangthuy.trolybabau.databinding.ItemBabyInfoBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ListBabyInfoAdapter extends BaseQuickAdapter<BabyInfo, BaseViewHolder> {
    public ListBabyInfoAdapter(@Nullable List<BabyInfo> data) {
        super(R.layout.item_baby_info, data);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, BabyInfo item) {
        ItemBabyInfoBinding binding = ItemBabyInfoBinding.bind(helper.itemView);
        binding.tvDate.setText(DateUtils.getDate(item.year, item.month, item.day));
        binding.tvWeightValue.setText(item.weight + " " + mContext.getResources().getString(R.string.tv_gram));
        String week = item.week + " " + mContext.getResources().getString(R.string.tv_tuan);
        binding.tvAgeValue.setText(item.day != 0 ? week + " " + item.day + " " + mContext.getResources().getString(R.string.tv_ngay) : week);
        if (item.images != null && !item.images.isEmpty()) {
            binding.ivImage.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(item.images);
            Glide.with(mContext).load(uri)
                    .into(binding.ivImage);
        } else {
            binding.ivImage.setVisibility(View.GONE);
        }
    }
}
