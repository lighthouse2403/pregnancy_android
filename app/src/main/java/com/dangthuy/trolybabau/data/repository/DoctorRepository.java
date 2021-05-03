package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.DoctorResponse;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class DoctorRepository extends BaseRepository {
    public DoctorRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadDoctorListener {
        void onLoadDoctorFinished(DoctorResponse response);
    }

    public void loadDoctor(int raw, LoadDoctorListener listener) {
        loadDataFromRaw(DoctorResponse.class, raw, listener::onLoadDoctorFinished);
    }
}
