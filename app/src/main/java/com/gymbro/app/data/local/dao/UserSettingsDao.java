package com.gymbro.app.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gymbro.app.data.local.entities.UserSettingsEntity;

@Dao
public interface UserSettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserSettingsEntity settings);

    @Query("SELECT * FROM user_settings WHERE userId = :userId")
    UserSettingsEntity getByUserId(String userId);

    @Query("DELETE FROM user_settings")
    void deleteAll();
}