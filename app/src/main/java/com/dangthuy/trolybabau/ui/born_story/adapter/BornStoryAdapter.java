package com.dangthuy.trolybabau.ui.born_story.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BornStory;
import com.dangthuy.trolybabau.databinding.ItemBornStoryBinding;

import java.util.List;

/**
 * Created by nhongthai on 3/25/2021.
 */
public class BornStoryAdapter extends BaseQuickAdapter<BornStory, BaseViewHolder> {
    public BornStoryAdapter(@Nullable List<BornStory> data) {
        super(R.layout.item_born_story, data);
    }

    public interface IClickListener{
        void onClick(BornStory item);
    }

    private IClickListener listener;

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, BornStory item) {
        ItemBornStoryBinding binding = ItemBornStoryBinding.bind(helper.itemView);
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
        binding.tvTitle.setText(item.getTitle());
    }
}
