package com.dangthuy.trolybabau.ui.born_story;

import android.app.Application;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.BornStory;
import com.dangthuy.trolybabau.data.repository.BornStoryRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/24/2021.
 */
public class BornStoryViewModel extends BaseViewModel {
    private final MutableLiveData<List<BornStory>> bornStories = new MutableLiveData<>();
    private BornStory mItem;
    private final BornStoryRepository.LoadBornStoryListener storiesListener = response -> {
        if (response != null && response.getData() != null) {
            bornStories.postValue(response.getData());
        }
    };

    public BornStoryViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        new BornStoryRepository(mContext).loadBornStory(R.raw.stories, storiesListener);
    }

    public MutableLiveData<List<BornStory>> getBornStories() {
        return bornStories;
    }

    public void setItem(BornStory item) {
        this.mItem = item;
    }

    public BornStory getmItem() {
        return mItem;
    }
}
