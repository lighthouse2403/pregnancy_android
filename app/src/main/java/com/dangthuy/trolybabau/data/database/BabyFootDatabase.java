package com.dangthuy.trolybabau.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dangthuy.trolybabau.data.dao.BabyFootDao;
import com.dangthuy.trolybabau.data.model.BabyFoot;

/**
 * Created by nhongthai on 4/11/2021.
 */
@Database(entities = {BabyFoot.class}, version = 1)
public abstract class BabyFootDatabase extends RoomDatabase {
    public abstract BabyFootDao babyFootDao();
}
