package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.BabyIndexResponse;
import com.dangthuy.trolybabau.data.response.PregnancyResponse;

/**
 * Created by nhongthai on 4/11/2021.
 */
public class PregnancyRepository extends BaseRepository{
    public PregnancyRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadPregnancyListener {
        void onLoadPregnancyFinished(PregnancyResponse response);
    }

    public interface LoadBabyIndexListener {
        void onLoadBabyIndexFinished(BabyIndexResponse response);
    }

    public void loadPregnancy(int raw, LoadPregnancyListener listener) {
        loadDataFromRaw(PregnancyResponse.class, raw, listener::onLoadPregnancyFinished);
    }

    public void loadBabyIndex(int raw, LoadBabyIndexListener listener) {
        loadDataFromRaw(BabyIndexResponse.class, raw, listener::onLoadBabyIndexFinished);
    }
}
