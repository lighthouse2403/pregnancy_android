package com.dangthuy.trolybabau.ui.share_corner.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;

import java.util.List;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareAdapter extends BaseQuickAdapter {
    public ShareAdapter(@Nullable List data) {
        super(R.layout.item_share, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
