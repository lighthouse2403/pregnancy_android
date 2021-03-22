package com.dangthuy.trolybabau.ui.home.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.databinding.ItemHomeMenuBinding;

import java.util.List;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeAdapter extends BaseQuickAdapter<HomeMenu, BaseViewHolder> {

    public interface IClickItemListener {
        void onClick(HomeMenu item);
    }

    private IClickItemListener listener;

    public void setListener(IClickItemListener listener) {
        this.listener = listener;
    }

    public HomeAdapter(@Nullable List<HomeMenu> data) {
        super(R.layout.item_home_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMenu item) {
        ItemHomeMenuBinding binding = ItemHomeMenuBinding.bind(helper.itemView);
        binding.tvTitle.setText(item.getTitle());
        binding.clContent.setOnClickListener(view -> listener.onClick(item));
    }
}
