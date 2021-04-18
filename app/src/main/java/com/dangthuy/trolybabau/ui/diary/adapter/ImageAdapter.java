package com.dangthuy.trolybabau.ui.diary.adapter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.databinding.ItemImageBinding;

import java.io.IOException;
import java.util.List;

/**
 * Created by nhongthai on 4/18/2021.
 */
public class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ImageAdapter(@Nullable List<String> data) {
        super(R.layout.item_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ItemImageBinding binding = ItemImageBinding.bind(helper.itemView);
        Uri uri = Uri.parse(item);
        Glide.with(mContext).load(uri)
                .error(R.drawable.after_pregnancy)
                .into(binding.ivItem);
    }
}
