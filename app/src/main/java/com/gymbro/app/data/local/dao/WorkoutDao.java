package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.WorkoutEntity;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WorkoutEntity> workouts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorkoutEntity workout);

    @Query("SELECT * FROM workouts ORDER BY completedAt DESC")
    List<WorkoutEntity> getAll();

    @Query("SELECT * FROM workouts WHERE id = :id")
    WorkoutEntity getById(String id);

    @Query("DELETE FROM workouts WHERE id = :id")
    void deleteById(String id);

    @Query("DELETE FROM workouts")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM workouts")
    int count();
}