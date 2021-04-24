package com.dangthuy.trolybabau.data.repository;

import android.content.Context;
import android.util.Log;

import com.dangthuy.trolybabau.data.response.BabyNameResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by nhongthai on 4/6/2021.
 */
public class HomeRepository extends BaseRepository {
    public HomeRepository(Context mContext) {
        super(mContext);
    }

}
