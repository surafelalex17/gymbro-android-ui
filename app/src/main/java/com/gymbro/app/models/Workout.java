package com.gymbro.app.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Workout {

    // ── Backend fields (from API) ─────────────────────────────────────────────
    @SerializedName("id")
    private String id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;

    @SerializedName("duration")
    private int duration; // in minutes

    @SerializedName("caloriesBurned")
    private Integer caloriesBurned;

    @SerializedName("notes")
    private String notes;

    @SerializedName("completedAt")
    private String completedAt;

    @SerializedName("createdAt")
    private String createdAt;

    // ── Local-only fields (for active workout tracking) ───────────────────────
    private long durationSeconds;
    private List<WorkoutExercise> exercises;
    private int totalVolume;
    private boolean isCompleted;

    // ── Constructors ──────────────────────────────────────────────────────────
    public Workout() {
        this.exercises = new ArrayList<>();
    }

    // ── Business logic ────────────────────────────────────────────────────────
    public int calculateTotalVolume() {
        int volume = 0;
        if (exercises == null) return 0;
        for (WorkoutExercise we : exercises) {
            for (WorkoutSet set : we.getSets()) {
                if (set.isCompleted()) {
                    volume += (int)(set.getWeight() * set.getReps());
                }
            }
        }
        return volume;
    }

    public int getTotalSetsCompleted() {
        int count = 0;
        if (exercises == null) return 0;
        for (WorkoutExercise we : exercises) {
            count += we.getCompletedSetsCount();
        }
        return count;
    }

    // ── Getters ───────────────────────────────────────────────────────────────
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
    public long getDurationSeconds() { return durationSeconds; }
    public List<WorkoutExercise> getExercises() { return exercises; }
    public int getTotalVolume() { return totalVolume; }
    public boolean isCompleted() { return isCompleted; }

    // ── Setters ───────────────────────────────────────────────────────────────
    public void setId(String id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setCaloriesBurned(Integer caloriesBurned) { this.caloriesBurned = caloriesBurned; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setCompletedAt(String completedAt) { this.completedAt = completedAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setDurationSeconds(long durationSeconds) { this.durationSeconds = durationSeconds; }
    public void setExercises(List<WorkoutExercise> exercises) { this.exercises = exercises; }
    public void setTotalVolume(int totalVolume) { this.totalVolume = totalVolume; }
    public void setCompleted(boolean completed) { this.isCompleted = completed; }
}