package com.dangthuy.trolybabau.ui.knowledge.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Knowledge;
import com.dangthuy.trolybabau.databinding.ItemCommonBinding;

import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DetailKnowledgeAdapter extends BaseQuickAdapter<Knowledge, BaseViewHolder> {
    public DetailKnowledgeAdapter(@Nullable List<Knowledge> data) {
        super(R.layout.item_common, data);
    }

    public interface IClickListener {
        void onClick(Knowledge item);
    }

    private IClickListener listener;

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Knowledge item) {
        ItemCommonBinding binding = ItemCommonBinding.bind(helper.itemView);
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
    }
}
