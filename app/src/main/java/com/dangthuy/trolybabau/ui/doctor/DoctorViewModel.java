package com.dangthuy.trolybabau.ui.doctor;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Doctor;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.data.repository.DoctorRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class DoctorViewModel extends BaseViewModel {

    private final MutableLiveData<List<Doctor>> doctorLiveData = new MutableLiveData<>();
    private List<Doctor> mData;
    private DatabaseReference mDatabase;
    private final DoctorRepository.LoadDoctorListener doctorListener = response -> {
        if (response != null && response.getDoctorList() != null) {
            mData = new ArrayList<>();
            if (response.getDoctorList().get_125thaithinh() != null) {
                mData.add(response.getDoctorList().get_125thaithinh());
            }
            if (response.getDoctorList().getAnhnd() != null) {
                mData.add(response.getDoctorList().getAnhnd());
            }
            if (response.getDoctorList().getChuongdh() != null) {
                mData.add(response.getDoctorList().getChuongdh());
            }
            if (response.getDoctorList().getChuongnc() != null) {
                mData.add(response.getDoctorList().getChuongnc());
            }
            if (response.getDoctorList().getCuongtd() != null) {
                mData.add(response.getDoctorList().getCuongtd());
            }
            if (response.getDoctorList().getDucpd() != null) {
                mData.add(response.getDoctorList().getDucpd());
            }
            if (response.getDoctorList().getDung() != null) {
                mData.add(response.getDoctorList().getDung());
            }
            if (response.getDoctorList().getDungpd() != null) {
                mData.add(response.getDoctorList().getDungpd());
            }
            if (response.getDoctorList().getDuyenlt() != null) {
                mData.add(response.getDoctorList().getDuyenlt());
            }
            if (response.getDoctorList().getHanh() != null) {
                mData.add(response.getDoctorList().getHanh());
            }
            if (response.getDoctorList().getKhanhvc() != null) {
                mData.add(response.getDoctorList().getKhanhvc());
            }
            if (response.getDoctorList().getLandtn() != null) {
                mData.add(response.getDoctorList().getLandtn());
            }
            if (response.getDoctorList().getNguyetnm() != null) {
                mData.add(response.getDoctorList().getNguyetnm());
            }
            if (response.getDoctorList().getNhapb() != null) {
                mData.add(response.getDoctorList().getNhapb());
            }
            if (response.getDoctorList().getQuypv() != null) {
                mData.add(response.getDoctorList().getQuypv());
            }
            if (response.getDoctorList().getThucdv() != null) {
                mData.add(response.getDoctorList().getThucdv());
            }
            if (response.getDoctorList().getTuan() != null) {
                mData.add(response.getDoctorList().getTuan());
            }
            if (response.getDoctorList().getVydh() != null) {
                mData.add(response.getDoctorList().getVydh());
            }
            doctorLiveData.postValue(mData);
        }
    };
    private Doctor mDoctor;

    public DoctorViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
//        new DoctorRepository(mContext).loadDoctor(R.raw.doctor, doctorListener);
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("doctor_list")
                .limitToFirst(10);
//        List<Share> shares = new ArrayList<>();
        mData = new ArrayList<>();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("thainh", "onChildAdded");
                Doctor doctor = snapshot.getValue(Doctor.class);
                if (doctor != null) {
                    if (doctor.getStar() != null && !doctor.getStar().isEmpty()) {
                        String[] splitStar = doctor.getStar().split(",");
                        int sum = 0;
                        for (String s : splitStar) {
                            sum += Integer.parseInt(s);
                        }
                        int avarate = sum / splitStar.length;
                        doctor.setStarRank(avarate);
                    }
                    Log.d("thainh", doctor.toString());
                    mData.add(doctor);
                    new Handler().postDelayed(() -> doctorLiveData.postValue(mData), 500);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("thainh", "onChildChanged");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    Log.d("thainh", share.toString());
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d("thainh", "onChildRemoved");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    Log.d("thainh", share.toString());
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("thainh", "onChildMoved");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    Log.d("thainh", share.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("thainh", "onCancelled " + error);
            }
        });
    }

    public MutableLiveData<List<Doctor>> getDoctorLiveData() {
        return doctorLiveData;
    }

    public void searchByName(String name) {
        List<Doctor> doctorList = new ArrayList<>();
        for (Doctor doctor : mData) {
            if (doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                doctorList.add(doctor);
            }
        }
        doctorLiveData.postValue(doctorList);
    }

    public void setDoctor(Doctor doctor) {
        this.mDoctor = doctor;
    }

    public Doctor getDoctor() {
        return mDoctor;
    }

    public String getPhone() {
        return mDoctor.getPhone().trim().replace(".", "").replace(" ", "");
    }
}
