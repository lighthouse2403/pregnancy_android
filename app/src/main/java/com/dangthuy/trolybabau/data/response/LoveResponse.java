package com.dangthuy.trolybabau.data.response;

import com.dangthuy.trolybabau.data.model.Comment;
import com.google.firebase.database.DatabaseError;

/**
 * Created by nhongthai on 6/6/2021.
 */
public class LoveResponse extends BaseFirebaseResponse{
    private Comment comment;
    private int position;

    public LoveResponse(DatabaseError error, Comment comment, int position) {
        super(error);
        this.comment = comment;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public Comment getComment() {
        return comment;
    }
}
