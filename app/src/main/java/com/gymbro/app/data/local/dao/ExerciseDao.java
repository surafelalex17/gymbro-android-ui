package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.ExerciseEntity;

import java.util.List;

@Dao
public interface ExerciseDao {

    // Insert or replace if exists (for cache refresh)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ExerciseEntity> exercises);

    // Get all exercises
    @Query("SELECT * FROM exercises ORDER BY name ASC")
    List<ExerciseEntity> getAll();

    // Search by name or category
    @Query("SELECT * FROM exercises WHERE " +
            "name LIKE '%' || :query || '%' OR " +
            "category LIKE '%' || :query || '%' " +
            "ORDER BY name ASC")
    List<ExerciseEntity> search(String query);

    // Filter by category
    @Query("SELECT * FROM exercises WHERE category = :category ORDER BY name ASC")
    List<ExerciseEntity> getByCategory(String category);

    // Get all unique categories
    @Query("SELECT DISTINCT category FROM exercises ORDER BY category ASC")
    List<String> getCategories();

    // Count total
    @Query("SELECT COUNT(*) FROM exercises")
    int count();

    // Clear all (before refresh)
    @Query("DELETE FROM exercises")
    void deleteAll();
}