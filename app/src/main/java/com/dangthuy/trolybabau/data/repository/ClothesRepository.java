package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.ClothesResponse;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class ClothesRepository extends BaseRepository {
    public ClothesRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadClothesHospitalListener {
        void onLoadClothesHospitalFinished(ClothesResponse response);
    }

    public void loadClothesHospital(int raw, LoadClothesHospitalListener listener) {
        loadDataFromRaw(ClothesResponse.class, raw, listener::onLoadClothesHospitalFinished);
    }

}
