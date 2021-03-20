package com.dangthuy.trolybabau.ui.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by nhongthai on 8/3/2020.
 */
public abstract class BaseViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    protected Context mContext;
    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mContext = getApplication().getApplicationContext();
    }

    protected final MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLiveData() {
        return liveData;
    }

}
