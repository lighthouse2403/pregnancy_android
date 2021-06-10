package com.dangthuy.trolybabau.ui.share_corner;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.Comment;
import com.dangthuy.trolybabau.data.model.Share;
import com.dangthuy.trolybabau.data.response.LoveResponse;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nhongthai on 3/22/2021.
 */
public class ShareCornerViewModel extends BaseViewModel {
    private final MutableLiveData<Share> shareLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Share>> sharesLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Comment>> liveComment = new MutableLiveData<>();
    private final MutableLiveData<LoveResponse> liveError = new MutableLiveData<>();
    private List<Share> shares;

    private static final String CONTENT = "content";
    private static final String LIKE = "like";
    private static final String NAME = "name";
    private static final String TIME = "time";
    private static final String USERNAME = "userName";
    private static final String COMMENT = "discuss/comment";
    private static final String THREADS = "discuss/threads";

    public static final int TYPE_ALL = 0;
    public static final int TYPE_HOT = 2;
    private int mType;

    public ShareCornerViewModel(@NonNull Application application) {
        super(application);
    }

    private Share mShare;
    private DatabaseReference mDatabase;

    public void fetchData(int type) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = mDatabase.child(THREADS)
                .orderByChild("time")
                .limitToLast(10);
        shares = new ArrayList<>();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("thainh", "onChildAdded");
                Share share = snapshot.getValue(Share.class);
                if (share != null) {
                    share.setKey(snapshot.getKey());
                    Log.d("thainh", share.toString());
                    if (type == TYPE_HOT) {
                        if (share.getFavorite() != null && share.getFavorite().length() > 0) {
                            shares.add(share);
                        }
                    } else {
                        shares.add(share);
                    }
                    Collections.reverse(shares);
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

        Query query = mDatabase
                .child(COMMENT)
                .child(key);
        ArrayList<Comment> list = new ArrayList<>();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("thainh", "snapshot.toString " + snapshot.toString());
                snapshot.getChildren().forEach(dataSnapshot -> {
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    comment.setKey(dataSnapshot.getKey());
                    list.add(comment);
                });
                liveComment.postValue(list);

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
        Share share = new Share(content, sharedPrefs.get(Constants.MOM_NAME, String.class), System.currentTimeMillis(), title, UUID.randomUUID().toString());
        mDatabase.child(THREADS)
                .push()
                .setValue(share, (error, ref) ->
                        Log.d("thainh", "error " + error));
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

    public void sendComment(Share share, String content) {
        Comment comment = new Comment(content, "", "Ẩn danh", System.currentTimeMillis(), UUID.randomUUID().toString());
        mDatabase.child(COMMENT + "/" + share.getKey())
                .push()
                .setValue(comment, (error, ref) -> {
                    if (error != null) {
                        share.setLastComment(System.currentTimeMillis());
                        mDatabase.child(THREADS)
                                .child(share.getKey())
                                .updateChildren(share.toMap(), (error1, ref1) -> {
                                    Log.d("thainh", "error1 " + error1);
                                });
                    }
                });
    }

    public void sendLove(String key, Comment item, int position) {
        item.setLike(item.getLike() + "," + UUID.randomUUID().toString());
        mDatabase.child(COMMENT)
                .child(key)
                .child(item.getKey())
                .updateChildren(item.toMap(), (error, ref) -> {
                    LoveResponse response = new LoveResponse(error, item, position);
                    liveError.postValue(response);
                });
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

    public void setType(int type) {
        this.mType = type;
    }

    public int getType() {
        return mType;
    }

    public MutableLiveData<LoveResponse> getLiveError() {
        return liveError;
    }

    public void viewShare() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mShare.setViews(mShare.getViews() + 1);
        mDatabase.child(THREADS)
                .child(mShare.getKey())
                .updateChildren(mShare.toMap(), (error, ref) -> Log.d("thainh", "error " + error));
    }

    public void doStar() {
        String favorite = mShare.getFavorite() == null ? "" : mShare.getFavorite();
        mShare.setFavorite(favorite + "," + UUID.randomUUID().toString());
        mDatabase.child(THREADS)
                .child(mShare.getKey())
                .updateChildren(mShare.toMap(), (error, ref) -> Log.d("thainh", "error " + error));
    }
}
