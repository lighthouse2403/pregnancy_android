package com.dangthuy.trolybabau.ui.born_story;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.data.model.BornStory;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/24/2021.
 */
public class BornStoryViewModel extends BaseViewModel {
    private final MutableLiveData<List<BornStory>> bornStories = new MutableLiveData<>();

    public BornStoryViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<BornStory> list = new ArrayList<>();
        list.add(new BornStory());
        list.add(new BornStory());
        list.add(new BornStory());
        bornStories.postValue(list);
    }

    public MutableLiveData<List<BornStory>> getBornStories() {
        return bornStories;
    }
}
