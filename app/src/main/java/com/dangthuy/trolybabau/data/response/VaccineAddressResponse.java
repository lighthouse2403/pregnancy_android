package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.VaccineAddress;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class VaccineAddressResponse extends BaseResponse{
    @SerializedName("ha_noi")
    private List<VaccineAddress> hnData;
    @SerializedName("ho_chi_minh")
    private List<VaccineAddress> hcmData;

    public List<VaccineAddress> getHnData() {
        return hnData;
    }

    public List<VaccineAddress> getHcmData() {
        return hcmData;
    }
}
