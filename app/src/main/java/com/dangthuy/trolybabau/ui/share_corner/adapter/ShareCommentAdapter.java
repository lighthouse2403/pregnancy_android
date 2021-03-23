package com.dangthuy.trolybabau.ui.share_corner.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Comment;

import java.util.List;

/**
 * Created by nhongthai on 3/23/2021.
 */
public class ShareCommentAdapter extends BaseQuickAdapter<Comment, BaseViewHolder> {
    public ShareCommentAdapter(@Nullable List<Comment> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {

    }
}
