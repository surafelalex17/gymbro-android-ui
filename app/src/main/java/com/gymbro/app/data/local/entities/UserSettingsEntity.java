package com.gymbro.app.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "user_settings")
public class UserSettingsEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String userId;
    private String weightUnit;
    private String heightUnit;
    private String theme;
    private int weeklyGoal;
    private boolean notificationsEnabled;

    public UserSettingsEntity(@NonNull String id, String userId,
                              String weightUnit, String heightUnit,
                              String theme, int weeklyGoal,
                              boolean notificationsEnabled) {
        this.id = id;
        this.userId = userId;
        this.weightUnit = weightUnit;
        this.heightUnit = heightUnit;
        this.theme = theme;
        this.weeklyGoal = weeklyGoal;
        this.notificationsEnabled = notificationsEnabled;
    }

    @NonNull public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getWeightUnit() { return weightUnit; }
    public String getHeightUnit() { return heightUnit; }
    public String getTheme() { return theme; }
    public int getWeeklyGoal() { return weeklyGoal; }
    public boolean isNotificationsEnabled() { return notificationsEnabled; }
}