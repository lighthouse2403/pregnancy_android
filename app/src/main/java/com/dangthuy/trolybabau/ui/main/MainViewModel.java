package com.dangthuy.trolybabau.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.ui.base.BaseViewModel;
import com.google.android.gms.ads.AdRequest;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class MainViewModel extends BaseViewModel {
    private final MutableLiveData<AdRequest> liveAd = new MutableLiveData<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadAds() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        liveAd.postValue(adRequest);
    }

    public MutableLiveData<AdRequest> getLiveAd() {
        return liveAd;
    }
}
