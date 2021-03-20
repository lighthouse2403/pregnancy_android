package com.dangthuy.trolybabau.ui.home.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.HomeMenu;

import java.util.List;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class HomeAdapter extends BaseQuickAdapter<HomeMenu, BaseViewHolder> {
    public HomeAdapter(@Nullable List<HomeMenu> data) {
        super(R.layout.item_home_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMenu item) {

    }
}
