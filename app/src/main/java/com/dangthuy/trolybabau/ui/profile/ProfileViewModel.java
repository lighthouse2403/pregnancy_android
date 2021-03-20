package com.dangthuy.trolybabau.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

/**
 * Created by nhongthai on 20/03/2021.
 */
public class ProfileViewModel extends BaseViewModel {
    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveData() {
        sharedPrefs.put(Constants.SET_UP, true);
    }
}
