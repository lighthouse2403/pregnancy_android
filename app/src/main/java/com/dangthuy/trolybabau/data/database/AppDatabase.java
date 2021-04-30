package com.dangthuy.trolybabau.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dangthuy.trolybabau.data.dao.AlarmDao;
import com.dangthuy.trolybabau.data.dao.BabyFootDao;
import com.dangthuy.trolybabau.data.dao.DiaryDao;
import com.dangthuy.trolybabau.data.dao.MomWeightDao;
import com.dangthuy.trolybabau.data.model.Alarm;
import com.dangthuy.trolybabau.data.model.BabyFoot;
import com.dangthuy.trolybabau.data.model.Diary;
import com.dangthuy.trolybabau.data.model.MomWeight;

/**
 * Created by nhongthai on 4/11/2021.
 */
@Database(entities = {BabyFoot.class, MomWeight.class, Diary.class, Alarm.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BabyFootDao babyFootDao();
    public abstract MomWeightDao momWeightDao();
    public abstract DiaryDao diaryDao();
    public abstract AlarmDao alarmDao();
}
