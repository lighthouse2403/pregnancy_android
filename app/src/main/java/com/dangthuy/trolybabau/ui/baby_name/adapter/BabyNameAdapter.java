package com.dangthuy.trolybabau.ui.baby_name.adapter;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyName;
import com.dangthuy.trolybabau.data.model.BabyNameDetail;
import com.dangthuy.trolybabau.databinding.ItemBabyNameBinding;
import com.dangthuy.trolybabau.ui.baby_name.BabyNameViewModel;

import java.util.List;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameAdapter extends BaseQuickAdapter<BabyNameDetail, BaseViewHolder> {
    public BabyNameAdapter(@Nullable List<BabyNameDetail> data) {
        super(R.layout.item_baby_name, data);
    }

    public interface ILoveListener {
        void onLoved(BabyNameDetail item, int position);
    }

    private ILoveListener loveListener;

    public void setLoveListener(ILoveListener loveListener) {
        this.loveListener = loveListener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, BabyNameDetail item) {
        ItemBabyNameBinding binding = ItemBabyNameBinding.bind(helper.itemView);
        binding.tvName.setText(item.getFirstName() + " " + item.getLastName());
        if (item.getStatus() == 0) {
            binding.btnLove.setVisibility(View.GONE);
        } else {
            binding.btnLove.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getStatus() == BabyNameViewModel.UNLOVED ? R.drawable.ic_star : R.drawable.ic_star_love).into(binding.btnLove);
        }
        binding.btnLove.setOnClickListener(view -> loveListener.onLoved(item, helper.getAdapterPosition()));
    }
}
