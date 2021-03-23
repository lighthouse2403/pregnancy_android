package com.dangthuy.trolybabau.ui.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.common.sharePrefs.SharedPrefsImpl;
import com.dangthuy.trolybabau.common.utils.Constants;

/**
 * Created by nhongthai on 8/3/2020.
 */
public abstract class BaseViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    protected Context mContext;

    protected SharedPrefsImpl sharedPrefs;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mContext = getApplication().getApplicationContext();
        this.sharedPrefs = SharedPrefsImpl.newInstance(Constants.CACHE, mContext);
    }

    protected final MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLiveData() {
        return liveData;
    }

    protected final MutableLiveData<String> liveToast = new MutableLiveData<>();

    public MutableLiveData<String> getLiveToast() {
        return liveToast;
    }
}
