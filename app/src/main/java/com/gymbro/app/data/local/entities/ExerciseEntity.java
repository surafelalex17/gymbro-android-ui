package com.gymbro.app.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;

import com.gymbro.app.data.local.Converters;

@Entity(tableName = "exercises")
@TypeConverters(Converters.class)
public class ExerciseEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String category;
    private String muscleGroups;
    private String equipment;
    private String description;

    public ExerciseEntity(@NonNull String id, String name, String category,
                          String muscleGroups, String equipment, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.muscleGroups = muscleGroups;
        this.equipment = equipment;
        this.description = description;
    }

    // Getters
    @NonNull
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getMuscleGroups() { return muscleGroups; }
    public String getEquipment() { return equipment; }
    public String getDescription() { return description; }
}