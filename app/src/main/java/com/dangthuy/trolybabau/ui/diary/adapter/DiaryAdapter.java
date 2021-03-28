package com.dangthuy.trolybabau.ui.diary.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Diary;

import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DiaryAdapter extends BaseQuickAdapter<Diary, BaseViewHolder> {
    public DiaryAdapter(@Nullable List<Diary> data) {
        super(R.layout.item_diary, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Diary item) {

    }
}
