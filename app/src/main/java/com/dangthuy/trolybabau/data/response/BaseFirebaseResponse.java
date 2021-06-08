package com.dangthuy.trolybabau.data.response;

import com.google.firebase.database.DatabaseError;

/**
 * Created by nhongthai on 6/6/2021.
 */
public class BaseFirebaseResponse extends BaseResponse{
    private DatabaseError error;

    public BaseFirebaseResponse(DatabaseError error) {
        this.error = error;
    }

    public DatabaseError getError() {
        return error;
    }
}
