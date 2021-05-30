package com.dangthuy.trolybabau.ui.share_corner;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Comment;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareCornerViewModel extends BaseViewModel {
    private final MutableLiveData<Share> shareLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Share>> sharesLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Comment>> liveComment = new MutableLiveData<>();
    private List<Share> shares;

    public ShareCornerViewModel(@NonNull Application application) {
        super(application);
    }

    private Share mShare;
    private DatabaseReference mDatabase;

    public void fetchData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("discuss/threads")
                .orderByChild("time")
                .limitToFirst(2);
        shares = new ArrayList<>();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("thainh", "onChildAdded");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    share.setKey(snapshot.getKey());
                    Log.d("thainh", share.toString());
                    shares.add(share);
                    new Handler().postDelayed(() -> sharesLiveData.postValue(shares), 500);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("thainh", "onChildChanged");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    Log.d("thainh", share.toString());
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d("thainh", "onChildRemoved");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    Log.d("thainh", share.toString());
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("thainh", "onChildMoved");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    Log.d("thainh", share.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("thainh", "onCancelled " + error);
            }
        });
    }

    public void fetchComment(String key) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("discuss/comment")
                .orderByKey()
                .startAt(key)
                .limitToFirst(1);
        ArrayList<Comment> list = new ArrayList<>();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                snapshot.getChildren().forEach(dataSnapshot -> {
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    list.add(comment);
                });
                liveComment.postValue(list);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public MutableLiveData<Share> getShare() {
        return shareLiveData;
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

    public MutableLiveData<List<Comment>> getLiveComment() {
        return liveComment;
    }

    public MutableLiveData<List<Share>> getSharesLiveData() {
        return sharesLiveData;
    }

    public void sendComment(String key, String comment) {

    }

    public void search(String newText) {
        List<Share> list = new ArrayList<>();
        for (Share share : shares) {
            if (share.getTitle().toLowerCase().contains(newText)) {
                list.add(share);
            }
        }
        sharesLiveData.postValue(list);
    }

    public void setShares(List<Share> list) {
        this.shares = list;
    }
}
