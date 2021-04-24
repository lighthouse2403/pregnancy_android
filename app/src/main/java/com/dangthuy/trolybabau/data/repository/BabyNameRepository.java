package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.BabyNameResponse;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameRepository extends BaseRepository{
    public BabyNameRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadHomeListener {
        void onLoadBabyNameFinished(BabyNameResponse response);
    }

    public void loadBabyName(LoadHomeListener listener, int raw) {
        loadDataFromRaw(BabyNameResponse.class, raw, listener::onLoadBabyNameFinished);
    }
}
