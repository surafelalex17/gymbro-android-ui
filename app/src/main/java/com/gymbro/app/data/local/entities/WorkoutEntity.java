package com.gymbro.app.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "workouts")
public class WorkoutEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String userId;
    private String title;
    private String description;
    private String type;
    private int duration;
    private Integer caloriesBurned;
    private String notes;
    private String completedAt;
    private String createdAt;

    public WorkoutEntity(@NonNull String id, String userId, String title,
                         String description, String type, int duration,
                         Integer caloriesBurned, String notes,
                         String completedAt, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.notes = notes;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
    }

    // Getters
    @NonNull
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public int getDuration() { return duration; }
    public Integer getCaloriesBurned() { return caloriesBurned; }
    public String getNotes() { return notes; }
    public String getCompletedAt() { return completedAt; }
    public String getCreatedAt() { return createdAt; }
}