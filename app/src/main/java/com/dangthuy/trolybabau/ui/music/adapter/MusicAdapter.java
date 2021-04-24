package com.dangthuy.trolybabau.ui.music.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Music;
import com.dangthuy.trolybabau.databinding.ItemMusicBinding;

import java.util.List;

/**
 * Created by nhongthai on 3/25/2021.
 */
public class MusicAdapter extends BaseQuickAdapter<Music, BaseViewHolder> {
    public MusicAdapter(@Nullable List<Music> data) {
        super(R.layout.item_music, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Music item) {
        ItemMusicBinding binding = ItemMusicBinding.bind(helper.itemView);
        binding.tvTitle.setText(item.getTitle());
    }
}
