package com.dangthuy.trolybabau.ui.settings.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Wallpaper;
import com.dangthuy.trolybabau.databinding.ItemSettingWallpaperBinding;

import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class WallpaperSettingAdapter extends BaseQuickAdapter<Wallpaper, BaseViewHolder> {
    public WallpaperSettingAdapter(@Nullable List<Wallpaper> data) {
        super(R.layout.item_setting_wallpaper, data);
    }

    public interface IClickListener {
        void onClick(Wallpaper item, int position);
    }

    private IClickListener listener;

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Wallpaper item) {
        ItemSettingWallpaperBinding binding = ItemSettingWallpaperBinding.bind(helper.itemView);
        binding.ivSelect.setVisibility(item.isSelect() ? View.VISIBLE : View.GONE);
        binding.clContent.setBackgroundColor(item.getColor());
        binding.clContent.setOnClickListener(view -> listener.onClick(item, helper.getAdapterPosition()));
    }
}
