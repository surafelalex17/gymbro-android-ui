package com.gymbro.app.models;

// Represents one set during an active workout session
public class SessionSet {

    private float weight;
    private int reps;
    private boolean isDone;

    public SessionSet() {
        this.weight = 0;
        this.reps = 0;
        this.isDone = false;
    }

    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }

    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { this.isDone = done; }
}