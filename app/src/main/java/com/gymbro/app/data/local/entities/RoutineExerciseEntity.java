package com.gymbro.app.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "routine_exercises")
public class RoutineExerciseEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String routineId;
    private String exerciseId;
    private String exerciseName;
    private String exerciseCategory;
    private int sets;
    private int reps;
    private Float weight;
    private int order;

    public RoutineExerciseEntity(@NonNull String id, String routineId,
                                 String exerciseId, String exerciseName,
                                 String exerciseCategory, int sets,
                                 int reps, Float weight, int order) {
        this.id = id;
        this.routineId = routineId;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseCategory = exerciseCategory;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.order = order;
    }

    @NonNull public String getId() { return id; }
    public String getRoutineId() { return routineId; }
    public String getExerciseId() { return exerciseId; }
    public String getExerciseName() { return exerciseName; }
    public String getExerciseCategory() { return exerciseCategory; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public Float getWeight() { return weight; }
    public int getOrder() { return order; }
}