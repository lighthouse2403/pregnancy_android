package com.dangthuy.trolybabau.ui.diary;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.data.model.Diary;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dangthuy.trolybabau.MainActivity.appDatabase;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class DiaryViewModel extends BaseViewModel {
    private final MutableLiveData<List<String>> images = new MutableLiveData<>();
    private final MutableLiveData<List<Diary>> diaries = new MutableLiveData<>();
    private List<String> imageList;

    public DiaryViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        new Thread(() -> diaries.postValue(appDatabase.diaryDao().findAll())).start();
    }

    public MutableLiveData<List<Diary>> getDiaries() {
        return diaries;
    }

    public void saveDiaryToDb(String title, String content) {
        Diary diary = new Diary(title, content, DateUtils.formatDateDb(new Date()), new Gson().toJson(imageList));
        new Thread(() -> appDatabase.diaryDao().insert(diary)).start();
    }

    public MutableLiveData<List<String>> getImages() {
        return images;
    }

    public void fetchImages(Intent data) {
        imageList = new ArrayList<>();
        if (data != null) {
            if (data.getData() != null) {
                Uri uri = data.getData();
                imageList.add(uri.toString());
            } else if (data.getClipData() != null) {
                for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    imageList.add(uri.toString());
                }
            }
            images.postValue(imageList);
        }
    }
}
