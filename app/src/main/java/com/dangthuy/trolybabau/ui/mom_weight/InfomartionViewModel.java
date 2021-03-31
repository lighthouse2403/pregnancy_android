package com.dangthuy.trolybabau.ui.mom_weight;

import android.app.Application;

import androidx.annotation.NonNull;

import com.dangthuy.trolybabau.ui.base.BaseViewModel;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class InfomartionViewModel extends BaseViewModel {

    public InfomartionViewModel(@NonNull Application application) {
        super(application);
    }
    public static final int TYPE_MOM = 0;
    public static final int TYPE_BABY = 1;
    private int mType;

    public void setDate(int year, int month, int day) {

    }

    public void setType(int type) {
        this.mType = type;
    }

    public int getmType() {
        return mType;
    }
}
