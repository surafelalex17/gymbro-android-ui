package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.WorkoutExerciseEntity;

import java.util.List;

@Dao
public interface WorkoutExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WorkoutExerciseEntity> exercises);

    @Query("SELECT * FROM workout_exercises WHERE workoutId = :workoutId ORDER BY `order` ASC")
    List<WorkoutExerciseEntity> getByWorkoutId(String workoutId);

    @Query("DELETE FROM workout_exercises WHERE workoutId = :workoutId")
    void deleteByWorkoutId(String workoutId);

    @Query("DELETE FROM workout_exercises")
    void deleteAll();
}