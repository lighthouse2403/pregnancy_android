package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.NutriResponse;
import com.dangthuy.trolybabau.data.response.VaccineAddressResponse;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class VaccineAddressRepository extends BaseRepository{
    public VaccineAddressRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadVaccineAddress {
        void onLoadVaccineAddressFinished(VaccineAddressResponse response);
    }

    public void loadVaccineAddress(int raw, LoadVaccineAddress listener) {
        loadDataFromRaw(VaccineAddressResponse.class, raw, listener::onLoadVaccineAddressFinished);
    }
}
