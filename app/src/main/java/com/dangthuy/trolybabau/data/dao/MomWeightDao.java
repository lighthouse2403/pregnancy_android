package com.dangthuy.trolybabau.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dangthuy.trolybabau.data.model.BabyFoot;
import com.dangthuy.trolybabau.data.model.MomWeight;

import java.util.List;

/**
 * Created by nhongthai on 4/13/2021.
 */
@Dao
public interface MomWeightDao {
    @Query("SELECT * FROM MomWeight")
    List<MomWeight> findAll();

    @Insert
    void insert(MomWeight... momWeights);
}
