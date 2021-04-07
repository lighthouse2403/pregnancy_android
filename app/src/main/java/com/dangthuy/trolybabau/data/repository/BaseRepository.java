package com.dangthuy.trolybabau.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.IdRes;

import com.dangthuy.trolybabau.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by nhongthai on 4/6/2021.
 */
public abstract class BaseRepository {
    private static final String TAG = "BaseRepository";
    private Context mContext;

    public BaseRepository(Context mContext) {
        this.mContext = mContext;
    }

    protected interface LoadDataListener<X> {
        void onLoadFinished(X response);
    }

    protected <Y> void loadDataFromRaw(Class<Y> clazz, int raw, LoadDataListener<Y> listener) {
        try {
            InputStream in = mContext.getResources().openRawResource(raw);
            Reader rd = new BufferedReader(new InputStreamReader(in));
            Type collectionType = new TypeToken<Y>() {
            }.getType();
            Y y = new Gson().fromJson(rd, clazz);
            if (y != null) {
                listener.onLoadFinished(y);
            }
        } catch (Exception ex) {
            Log.d(TAG, "loadDataFromRaw error " + ex);
        }
    }
}
