package com.dangthuy.trolybabau.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dangthuy.trolybabau.data.model.Alarm;
import com.dangthuy.trolybabau.data.model.BabyFoot;

import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
@Dao
public interface AlarmDao {
    @Query("SELECT * FROM Alarm")
    List<Alarm> findAll();

    @Insert
    void insert(Alarm... alarms);

    @Update
    int update(Alarm alarm);
}
