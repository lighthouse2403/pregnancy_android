package com.dangthuy.trolybabau.ui.chiso.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BabyInfo;
import com.dangthuy.trolybabau.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ListBabyInfoAdapter extends BaseQuickAdapter<BabyInfo, BaseViewHolder> {
    public ListBabyInfoAdapter(@Nullable List<BabyInfo> data) {
        super(R.layout.item_baby_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BabyInfo item) {

    }
}
