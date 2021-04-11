package com.dangthuy.trolybabau.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dangthuy.trolybabau.data.model.BabyFoot;

import java.util.List;

/**
 * Created by nhongthai on 4/11/2021.
 */
@Dao
public interface BabyFootDao {
    @Query("SELECT * FROM BabyFoot")
    List<BabyFoot> findAll();

    @Insert
    void insert(BabyFoot... babyFoots);
}
