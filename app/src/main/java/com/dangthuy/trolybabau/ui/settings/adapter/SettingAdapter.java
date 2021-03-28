package com.dangthuy.trolybabau.ui.settings.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Setting;
import com.dangthuy.trolybabau.databinding.ItemCommonBinding;

import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class SettingAdapter extends BaseQuickAdapter<Setting, BaseViewHolder> {
    public SettingAdapter(@Nullable List<Setting> data) {
        super(R.layout.item_common, data);
    }

    public interface IClickListener {
        void onClick(Setting item);
    }

    private IClickListener listener;

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Setting item) {
        ItemCommonBinding binding = ItemCommonBinding.bind(helper.itemView);
        binding.tvTitle.setText(item.getTitle());
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
    }
}
