package com.dangthuy.trolybabau.ui.baby_name.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyName;
import com.dangthuy.trolybabau.data.model.BabyNameDetail;
import com.dangthuy.trolybabau.databinding.ItemBabyNameBinding;

import java.util.List;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameAdapter extends BaseQuickAdapter<BabyNameDetail, BaseViewHolder> {
    public BabyNameAdapter(@Nullable List<BabyNameDetail> data) {
        super(R.layout.item_baby_name, data);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, BabyNameDetail item) {
        ItemBabyNameBinding binding = ItemBabyNameBinding.bind(helper.itemView);
        binding.tvName.setText(item.getFirstName() + " " + item.getLastName());
    }
}
