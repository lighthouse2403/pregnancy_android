package com.dangthuy.trolybabau.ui.share_corner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Comment;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareCornerViewModel extends BaseViewModel {
    private final MutableLiveData<List<Share>> shares = new MutableLiveData<>();
    private final MutableLiveData<List<Comment>> comments = new MutableLiveData<>();
    public ShareCornerViewModel(@NonNull Application application) {
        super(application);
    }
    private Share mShare;

    public void fetchData() {
        ArrayList<Share> list = new ArrayList<>();
        list.add(new Share("Xin chào"));
        list.add(new Share("Hi"));
        list.add(new Share("Bạn khỏe không"));
        list.add(new Share("How are you"));
        shares.postValue(list);
    }

    public void fetchComment(){
        ArrayList<Comment> list = new ArrayList<>();
        list.add(new Comment());
        list.add(new Comment());
        comments.postValue(list);
    }

    public MutableLiveData<List<Share>> getShares() {
        return shares;
    }

    public void createShare(String title, String content) {
        if (!validate(title, content)) {
            liveToast.postValue(mContext.getResources().getString(R.string.tv_invalid_title_content));
        }
    }

    private boolean validate(String title, String content) {
        return (title != null && !title.isEmpty() && content != null && !content.isEmpty());
    }

    public Share getmShare() {
        return mShare;
    }

    public void setmShare(Share mShare) {
        this.mShare = mShare;
    }

    public MutableLiveData<List<Comment>> getComments() {
        return comments;
    }
}
