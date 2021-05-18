package com.dangthuy.trolybabau.data.sort;

import com.dangthuy.trolybabau.data.model.Doctor;

import java.util.Comparator;

/**
 * Created by nhongthai on 5/18/2021.
 */
public class DoctorSort implements Comparator<Doctor> {
    @Override
    public int compare(Doctor doctor, Doctor t1) {
        if (doctor.getStarRank() == t1.getStarRank())
            return 0;
        if (doctor.getStarRank() < t1.getStarRank())
            return 1;
        return -1;
    }
}
