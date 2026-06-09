package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.RoutineExerciseEntity;

import java.util.List;

@Dao
public interface RoutineExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RoutineExerciseEntity> exercises);

    @Query("SELECT * FROM routine_exercises WHERE routineId = :routineId ORDER BY `order` ASC")
    List<RoutineExerciseEntity> getByRoutineId(String routineId);

    @Query("DELETE FROM routine_exercises WHERE routineId = :routineId")
    void deleteByRoutineId(String routineId);

    @Query("DELETE FROM routine_exercises")
    void deleteAll();
}