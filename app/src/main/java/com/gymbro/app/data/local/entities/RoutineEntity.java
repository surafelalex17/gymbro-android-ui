package com.gymbro.app.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "routines")
public class RoutineEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String userId;
    private String name;
    private String description;
    private String split;
    private boolean isDefault;
    private String createdAt;

    public RoutineEntity(@NonNull String id, String userId, String name,
                         String description, String split,
                         boolean isDefault, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.split = split;
        this.isDefault = isDefault;
        this.createdAt = createdAt;
    }

    @NonNull public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSplit() { return split; }
    public boolean isDefault() { return isDefault; }
    public String getCreatedAt() { return createdAt; }
}