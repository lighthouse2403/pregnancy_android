package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Doctor;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class DoctorResponse extends BaseResponse{

    @SerializedName("doctor_list")
    private DoctorList doctorList;

    public DoctorList getDoctorList() {
        return doctorList;
    }

    public static class DoctorList {
        @SerializedName("125thaithinh")
        private Doctor _125thaithinh;
        @SerializedName("anhnd")
        private Doctor anhnd;
        @SerializedName("chuongdh")
        private Doctor chuongdh;
        @SerializedName("chuongnc")
        private Doctor chuongnc;
        @SerializedName("cuongtd")
        private Doctor cuongtd;
        @SerializedName("ducpd")
        private Doctor ducpd;
        @SerializedName("dung")
        private Doctor dung;
        @SerializedName("dungpd")
        private Doctor dungpd;
        @SerializedName("duyenlt")
        private Doctor duyenlt;
        @SerializedName("hanh")
        private Doctor hanh;
        @SerializedName("khanhvc")
        private Doctor khanhvc;
        @SerializedName("landtn")
        private Doctor landtn;
        @SerializedName("nguyetnm")
        private Doctor nguyetnm;
        @SerializedName("nhapb")
        private Doctor nhapb;
        @SerializedName("quypv")
        private Doctor quypv;
        @SerializedName("thucdv")
        private Doctor thucdv;
        @SerializedName("tuan")
        private Doctor tuan;
        @SerializedName("vydh")
        private Doctor vydh;

        public Doctor get_125thaithinh() {
            return _125thaithinh;
        }

        public Doctor getAnhnd() {
            return anhnd;
        }

        public Doctor getChuongdh() {
            return chuongdh;
        }

        public Doctor getChuongnc() {
            return chuongnc;
        }

        public Doctor getCuongtd() {
            return cuongtd;
        }

        public Doctor getDucpd() {
            return ducpd;
        }

        public Doctor getDung() {
            return dung;
        }

        public Doctor getDungpd() {
            return dungpd;
        }

        public Doctor getDuyenlt() {
            return duyenlt;
        }

        public Doctor getHanh() {
            return hanh;
        }

        public Doctor getKhanhvc() {
            return khanhvc;
        }

        public Doctor getLandtn() {
            return landtn;
        }

        public Doctor getNguyetnm() {
            return nguyetnm;
        }

        public Doctor getNhapb() {
            return nhapb;
        }

        public Doctor getQuypv() {
            return quypv;
        }

        public Doctor getThucdv() {
            return thucdv;
        }

        public Doctor getTuan() {
            return tuan;
        }

        public Doctor getVydh() {
            return vydh;
        }
    }
}
