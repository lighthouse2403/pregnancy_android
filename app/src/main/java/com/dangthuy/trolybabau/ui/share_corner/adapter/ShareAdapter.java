package com.dangthuy.trolybabau.ui.share_corner.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.databinding.ItemShareBinding;
import com.dangthuy.trolybabau.ui.home.adapter.HomeAdapter;

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

    @Override
    protected void convert(BaseViewHolder helper, Share item) {
        ItemShareBinding binding = ItemShareBinding.bind(helper.itemView);
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
    }
}
