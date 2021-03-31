package com.dangthuy.trolybabau.ui.mom_weight.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.MomWeight;

import java.util.List;


/**
 * Created by nhongthai on 3/29/2021.
 */
public class InformationAdapter extends BaseQuickAdapter<MomWeight, BaseViewHolder> {
    public InformationAdapter(@Nullable List<MomWeight> data) {
        super(R.layout.item_mom_weight ,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MomWeight item) {

    }
}
