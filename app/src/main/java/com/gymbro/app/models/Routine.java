package com.gymbro.app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a saved workout routine (template).
 * Users build these in Routine Builder and reuse them.
 */
public class Routine {

    private String id;
    private String name;
    private String description;
    private String split;           // e.g. "Push", "Pull", "Legs", "Full Body"
    private List<Exercise> exercises;
    private String createdAt;
    private boolean isDefault;      // Pre-built routines vs user-created

    public Routine() {
        this.exercises = new ArrayList<>();
    }

    public Routine(String id, String name, String description, String split) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.split = split;
        this.exercises = new ArrayList<>();
        this.isDefault = false;
    }

    public int getExerciseCount() {
        return exercises != null ? exercises.size() : 0;
    }

    // --- Getters and Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSplit() { return split; }
    public void setSplit(String split) { this.split = split; }

    public List<Exercise> getExercises() { return exercises; }
    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean aDefault) { isDefault = aDefault; }
}