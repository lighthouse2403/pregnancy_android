package com.dangthuy.trolybabau.ui.share_corner.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.Comment;
import com.dangthuy.trolybabau.databinding.ItemCommentBinding;
import com.dangthuy.trolybabau.listener.IClickItemListener;

import java.util.Date;
import java.util.List;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class ShareCommentAdapter extends BaseQuickAdapter<Comment, BaseViewHolder> {
    public ShareCommentAdapter(@Nullable List<Comment> data) {
        super(R.layout.item_comment, data);
    }

    private IClickItemListener<Comment> itemListener;

    public void setItemListener(IClickItemListener<Comment> itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {
        ItemCommentBinding binding = ItemCommentBinding.bind(helper.itemView);
        binding.tvName.setText(item.getName());
        binding.tvContent.setText(item.getContent());
        binding.tvTime.setText(DateUtils.formatDate(new Date(item.getTime())));
        if (item.getLike() !=null) {
            String split[] = item.getLike().split(",");
            if (split.length > 1) {
                binding.tvLove.setVisibility(View.VISIBLE);
                binding.tvLove.setText(String.valueOf(split.length - 1));
            } else {
                binding.tvLove.setVisibility(View.INVISIBLE);
            }
        }
        binding.ivLove.setOnClickListener(view -> {
            binding.ivLove.setColorFilter(ContextCompat.getColor(mContext, R.color.red));
            itemListener.onClick(item, helper.getAdapterPosition(), false);
        });
    }
}
