package com.dangthuy.trolybabau.ui.share_corner.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.databinding.ItemShareBinding;

import java.util.Date;
import java.util.List;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareAdapter extends BaseQuickAdapter<Share, BaseViewHolder> {

    public interface IClickItemListener {
        void onClick(Share item);
    }

    private IClickItemListener listener;

    public void setListener(IClickItemListener listener) {
        this.listener = listener;
    }

    public ShareAdapter(@Nullable List data) {
        super(R.layout.item_share, data);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, Share item) {
        ItemShareBinding binding = ItemShareBinding.bind(helper.itemView);
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
        binding.tvTitle.setText(item.getTitle());
        binding.tvCommentCount.setText(String.format(mContext.getResources().getString(R.string.tv_s_binh_luan), String.valueOf(item.getNumberOfComment())));
        binding.tvName.setText(item.getOwner());
        binding.tvViewCount.setText(String.format(mContext.getResources().getString(R.string.tv_s_da_doc), String.valueOf(item.getViews())));
        binding.tvTime.setText(DateUtils.formatDateTime(new Date(item.getTime())));
    }
}
