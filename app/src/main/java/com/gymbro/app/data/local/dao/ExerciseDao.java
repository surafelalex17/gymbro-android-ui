package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.ExerciseEntity;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ExerciseEntity> exercises);

    @Query("SELECT * FROM exercises ORDER BY name ASC")
    List<ExerciseEntity> getAll();

    @Query("SELECT * FROM exercises WHERE " +
            "name LIKE '%' || :query || '%' OR " +
            "category LIKE '%' || :query || '%' " +
            "ORDER BY name ASC")
    List<ExerciseEntity> search(String query);

    @Query("SELECT * FROM exercises WHERE category = :category ORDER BY name ASC")
    List<ExerciseEntity> getByCategory(String category);

    @Query("SELECT DISTINCT category FROM exercises ORDER BY category ASC")
    List<String> getCategories();

    @Query("SELECT COUNT(*) FROM exercises")
    int count();

    @Query("DELETE FROM exercises")
    void deleteAll();
}