package com.dangthuy.trolybabau.ui.vaccine;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.VaccineAddressSection;
import com.dangthuy.trolybabau.data.repository.VaccineAddressRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class VaccineAddressViewModel extends BaseViewModel {
    private final MutableLiveData<List<VaccineAddressSection>> vaccines = new MutableLiveData<>();
    private VaccineAddressRepository.LoadVaccineAddress addressListener = response -> {
        if (response != null) {
            List<VaccineAddressSection> list = new ArrayList<>();
            if (response.getHnData() != null) {
                VaccineAddressSection section = new VaccineAddressSection();
                section.setSection("Hà Nội");
                section.setAddresses(response.getHnData());
                list.add(section);
            }
            if (response.getHcmData() != null) {
                VaccineAddressSection section = new VaccineAddressSection();
                section.setSection("Hồ Chí Minh");
                section.setAddresses(response.getHcmData());
                list.add(section);
            }
            vaccines.postValue(list);
        }
    };

    public VaccineAddressViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        new VaccineAddressRepository(mContext).loadVaccineAddress(R.raw.vaccination, addressListener);
    }

    public MutableLiveData<List<VaccineAddressSection>> getVaccines() {
        return vaccines;
    }
}
