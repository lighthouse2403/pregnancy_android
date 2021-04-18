package com.dangthuy.trolybabau.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dangthuy.trolybabau.data.model.Diary;

import java.util.List;

/**
 * Created by nhongthai on 4/18/2021.
 */
@Dao
public interface DiaryDao {
    @Query("SELECT * FROM Diary")
    List<Diary> findAll();

    @Insert
    void insert(Diary... diaries);
}
