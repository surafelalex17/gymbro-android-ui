package com.gymbro.app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an exercise added to an active workout, including its sets.
 * Links an Exercise to its performed sets.
 */
public class WorkoutExercise {

    private Exercise exercise;
    private List<WorkoutSet> sets;
    private String notes;

    public WorkoutExercise() {
        this.sets = new ArrayList<>();
    }

    public WorkoutExercise(Exercise exercise) {
        this.exercise = exercise;
        this.sets = new ArrayList<>();
        // Add a default first set
        sets.add(new WorkoutSet(1, 0f, 0));
    }

    public void addSet() {
        int nextSetNumber = sets.size() + 1;
        float lastWeight = sets.isEmpty() ? 0f : sets.get(sets.size() - 1).getWeight();
        int lastReps = sets.isEmpty() ? 0 : sets.get(sets.size() - 1).getReps();
        sets.add(new WorkoutSet(nextSetNumber, lastWeight, lastReps));
    }

    public void removeLastSet() {
        if (!sets.isEmpty()) {
            sets.remove(sets.size() - 1);
        }
    }

    public int getCompletedSetsCount() {
        int count = 0;
        for (WorkoutSet set : sets) {
            if (set.isCompleted()) count++;
        }
        return count;
    }

    // --- Getters and Setters ---

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public List<WorkoutSet> getSets() { return sets; }
    public void setSets(List<WorkoutSet> sets) { this.sets = sets; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}