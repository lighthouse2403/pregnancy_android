package com.dangthuy.trolybabau.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dangthuy.trolybabau.data.model.BabyFoot;
import com.dangthuy.trolybabau.data.model.BabyInfo;

import java.util.List;

/**
 * Created by nhongthai on 5/2/2021.
 */
@Dao
public interface BabyInfoDao {
    @Query("SELECT * FROM BabyInfo")
    List<BabyInfo> findAll();

    @Insert
    void insert(BabyInfo... babyInfos);
}
