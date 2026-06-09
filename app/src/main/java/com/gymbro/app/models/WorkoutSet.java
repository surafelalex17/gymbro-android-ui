package com.gymbro.app.models;


public class WorkoutSet{

    private int setNumber;
    private float weight;       // in kg
    private int reps;
    private boolean isCompleted;
    private String rpe;         // Rate of Perceived Exertion (optional)
    private String notes;

    public WorkoutSet() {}

    public WorkoutSet(int setNumber, float weight, int reps) {
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
        this.isCompleted = false;
    }


    public int getSetNumber() { return setNumber; }
    public void setSetNumber(int setNumber) { this.setNumber = setNumber; }

    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public String getRpe() { return rpe; }
    public void setRpe(String rpe) { this.rpe = rpe; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}