package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Share;
import com.google.firebase.database.DatabaseError;

/**
 * Created by nhongthai on 7/6/2021.
 */
public class ThreadResponse extends BaseFirebaseResponse {
    private Share share;

    public ThreadResponse(DatabaseError error, Share share) {
        super(error);
        this.share = share;
    }
}
