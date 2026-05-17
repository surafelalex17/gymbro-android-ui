package com.gymbro.app.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Routine implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("split")
    private String split;

    @SerializedName("isDefault")
    private boolean isDefault;

    @SerializedName("createdAt")
    private String createdAt;

    // ← Changed from List<Exercise> to List<RoutineExercise>
    @SerializedName("exercises")
    private List<RoutineExercise> exercises;

    public Routine() {
        this.exercises = new ArrayList<>();
    }

    public int getExerciseCount() {
        return exercises != null ? exercises.size() : 0;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSplit() { return split; }
    public boolean isDefault() { return isDefault; }
    public String getCreatedAt() { return createdAt; }
    public List<RoutineExercise> getExercises() { return exercises; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setSplit(String split) { this.split = split; }
    public void setDefault(boolean aDefault) { this.isDefault = aDefault; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setExercises(List<RoutineExercise> exercises) { this.exercises = exercises; }
}