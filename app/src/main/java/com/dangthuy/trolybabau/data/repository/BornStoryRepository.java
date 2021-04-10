package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.BornStoryResponse;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class BornStoryRepository extends BaseRepository{
    public BornStoryRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadBornStoryListener {
        void onLoadDataFinished(BornStoryResponse response);
    }

    public void loadBornStory(int raw, LoadBornStoryListener listener) {
        loadDataFromRaw(BornStoryResponse.class, raw, listener::onLoadDataFinished);
    }
}
