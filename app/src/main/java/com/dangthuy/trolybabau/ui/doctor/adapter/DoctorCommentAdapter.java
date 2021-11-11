package com.dangthuy.trolybabau.ui.doctor.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.DoctorComment;
import com.dangthuy.trolybabau.databinding.ItemCommentBinding;

import java.util.Date;
import java.util.List;

/**
 * Created by nhongthai on 11/8/2021.
 */
public class DoctorCommentAdapter extends BaseQuickAdapter<DoctorComment, BaseViewHolder> {
    public DoctorCommentAdapter(@Nullable List<DoctorComment> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorComment item) {
        ItemCommentBinding binding = ItemCommentBinding.bind(helper.itemView);
        binding.tvName.setText(mContext.getString(R.string.tv_an_danh));
        binding.tvContent.setText(item.getContent());
        binding.tvTime.setText(DateUtils.formatDateTime(new Date(item.getTime())));
        if (item.getLike() != null && !item.getLike().isEmpty()) {
            if (item.getLike().contains(",")) {
                binding.tvLove.setText(String.valueOf(item.getLike().split(",").length));
            } else {
                binding.tvLove.setText("1");
            }
        } else {
            binding.tvLove.setText("0");
        }
    }
}
