package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.RoutineEntity;

import java.util.List;

@Dao
public interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RoutineEntity> routines);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoutineEntity routine);

    @Query("SELECT * FROM routines ORDER BY createdAt DESC")
    List<RoutineEntity> getAll();

    @Query("SELECT * FROM routines WHERE id = :id")
    RoutineEntity getById(String id);

    @Query("DELETE FROM routines WHERE id = :id")
    void deleteById(String id);

    @Query("DELETE FROM routines")
    void deleteAll();
}