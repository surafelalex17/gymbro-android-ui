package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.UserEntity;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity user);

    @Query("SELECT * FROM users WHERE id = :id")
    UserEntity getById(String id);

    @Query("DELETE FROM users")
    void deleteAll();
}