package com.dangthuy.trolybabau.ui.share_corner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareCornerViewModel extends BaseViewModel {
    private final MutableLiveData<List<Share>> shares = new MutableLiveData<>();
    public ShareCornerViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<Share> list = new ArrayList<>();
        list.add(new Share());
        list.add(new Share());
        list.add(new Share());
        list.add(new Share());
        list.add(new Share());
        list.add(new Share());
        list.add(new Share());
        list.add(new Share());
        list.add(new Share());
        shares.postValue(list);
    }

    public MutableLiveData<List<Share>> getShares() {
        return shares;
    }
}
