package com.gymbro.app.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RoutineExercise implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("routineId")
    private String routineId;

    @SerializedName("exerciseId")
    private String exerciseId;

    @SerializedName("sets")
    private int sets;

    @SerializedName("reps")
    private int reps;

    @SerializedName("weight")
    private Float weight;

    @SerializedName("order")
    private int order;

    @SerializedName("notes")
    private String notes;

    // Nested exercise object
    @SerializedName("exercise")
    private Exercise exercise;

    // Getters
    public String getId() { return id; }
    public String getRoutineId() { return routineId; }
    public String getExerciseId() { return exerciseId; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public Float getWeight() { return weight; }
    public int getOrder() { return order; }
    public String getNotes() { return notes; }
    public Exercise getExercise() { return exercise; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setRoutineId(String routineId) { this.routineId = routineId; }
    public void setExerciseId(String exerciseId) { this.exerciseId = exerciseId; }
    public void setSets(int sets) { this.sets = sets; }
    public void setReps(int reps) { this.reps = reps; }
    public void setWeight(Float weight) { this.weight = weight; }
    public void setOrder(int order) { this.order = order; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }
}