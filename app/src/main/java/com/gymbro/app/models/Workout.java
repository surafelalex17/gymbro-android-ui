package com.gymbro.app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model representing a completed or in-progress workout session.
 * Ready for Node.js backend integration.
 */
public class Workout {

    private String id;
    private String name;
    private String date;            // ISO format for backend: "2024-01-15"
    private long durationSeconds;   // Total workout duration
    private List<WorkoutExercise> exercises;
    private String notes;
    private int totalVolume;        // Total kg lifted (kg x reps summed)
    private boolean isCompleted;

    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public Workout(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.exercises = new ArrayList<>();
        this.isCompleted = false;
    }

    /** Calculate total volume (weight × reps across all sets) */
    public int calculateTotalVolume() {
        int volume = 0;
        for (WorkoutExercise we : exercises) {
            for (WorkoutSet set : we.getSets()) {
                if (set.isCompleted()) {
                    volume += (int)(set.getWeight() * set.getReps());
                }
            }
        }
        return volume;
    }

    /** Get count of total sets completed */
    public int getTotalSetsCompleted() {
        int count = 0;
        for (WorkoutExercise we : exercises) {
            count += we.getCompletedSetsCount();
        }
        return count;
    }

    // --- Getters and Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public long getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(long durationSeconds) { this.durationSeconds = durationSeconds; }

    public List<WorkoutExercise> getExercises() { return exercises; }
    public void setExercises(List<WorkoutExercise> exercises) { this.exercises = exercises; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public int getTotalVolume() { return totalVolume; }
    public void setTotalVolume(int totalVolume) { this.totalVolume = totalVolume; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}