package com.dangthuy.trolybabau.ui.music;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.data.model.Music;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/25/2021.
 */
public class MusicViewModel extends BaseViewModel {
    private final MutableLiveData<List<Music>> music = new MutableLiveData<>();

    public MusicViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<Music> list = new ArrayList<>();
        list.add(new Music("Nhạc bầu cho bé yêu 1", ""));
        list.add(new Music("Nhạc bầu cho bé yêu 2", ""));
        list.add(new Music("Nhạc bầu cho bé yêu 3", ""));
        list.add(new Music("Nhạc bầu cho bé yêu 4", ""));
        list.add(new Music("Nhạc bầu cho bé yêu 5", ""));
        music.postValue(list);
    }

    public MutableLiveData<List<Music>> getMusic() {
        return music;
    }
}
