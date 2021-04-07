package com.dangthuy.trolybabau.ui.knowledge.adapter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BornStory;
import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.databinding.ItemNutriBinding;
import com.dangthuy.trolybabau.ui.born_story.adapter.BornStoryAdapter;

import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class NutriAdapter extends BaseQuickAdapter<Nutri, BaseViewHolder> {
    public NutriAdapter(@Nullable List<Nutri> data) {
        super(R.layout.item_nutri, data);
    }

    public interface IClickListener{
        void onClick(Nutri item);
    }

    private IClickListener listener;

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Nutri item) {
        ItemNutriBinding binding = ItemNutriBinding.bind(helper.itemView);
        binding.tvTitle.setText(item.getName());
        binding.tvContent.setText(item.getShortDescription());
        final int resourceId = mContext.getResources().getIdentifier(item.getImage(), "drawable", mContext.getPackageName());
        Glide.with(mContext).load(resourceId).into(binding.ivImage);
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
    }
}
