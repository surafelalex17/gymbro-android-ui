package com.gymbro.app.data.remote.dto;

public class RoutineExerciseRequest {
    private String exerciseId;
    private int sets;
    private int reps;
    private Float weight;

    public RoutineExerciseRequest(String exerciseId, int sets,
                                  int reps, Float weight) {
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }
}