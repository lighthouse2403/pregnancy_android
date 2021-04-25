package com.dangthuy.trolybabau.ui.recipe.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.databinding.ItemRecipeBinding;

import java.util.List;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class RecipeAdapter extends BaseQuickAdapter<Nutri, BaseViewHolder> {
    public RecipeAdapter(@Nullable List<Nutri> data) {
        super(R.layout.item_recipe, data);
    }

    public interface IClickListener {
        void onClick(Nutri item);
    }

    private IClickListener listener;

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Nutri item) {
        ItemRecipeBinding binding = ItemRecipeBinding.bind(helper.itemView);
        final int resourceId = mContext.getResources().getIdentifier(item.getImage(), "drawable", mContext.getPackageName());
        Glide.with(mContext).load(resourceId).into(binding.ivImage);
        binding.tvTitle.setText(item.getName());
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
    }
}
