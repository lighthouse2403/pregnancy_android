package com.dangthuy.trolybabau.ui.diary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.data.model.Diary;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DiaryViewModel extends BaseViewModel {
    private final MutableLiveData<List<Diary>> diaries = new MutableLiveData<>();

    public DiaryViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<Diary> list = new ArrayList<>();
        list.add(new Diary());
        list.add(new Diary());
        diaries.postValue(list);
    }

    public MutableLiveData<List<Diary>> getDiaries() {
        return diaries;
    }
}
