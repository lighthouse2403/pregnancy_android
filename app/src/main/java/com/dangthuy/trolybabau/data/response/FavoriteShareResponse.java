package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Share;
import com.google.firebase.database.DatabaseError;

/**
 * Created by nhongthai on 6/15/2021.
 */
public class FavoriteShareResponse extends BaseFirebaseResponse{
    private Share share;

    public FavoriteShareResponse(DatabaseError error, Share share) {
        super(error);
        this.share = share;
    }
}
