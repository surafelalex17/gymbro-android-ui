package com.gymbro.app.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "workout_exercises")
public class WorkoutExerciseEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String workoutId;
    private String exerciseId;
    private String exerciseName;
    private Integer sets;
    private Integer reps;
    private Float weight;
    private Integer duration;
    private Float distance;
    private int order;

    public WorkoutExerciseEntity(@NonNull String id, String workoutId,
                                 String exerciseId, String exerciseName,
                                 Integer sets, Integer reps, Float weight,
                                 Integer duration, Float distance, int order) {
        this.id = id;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.duration = duration;
        this.distance = distance;
        this.order = order;
    }

    @NonNull public String getId() { return id; }
    public String getWorkoutId() { return workoutId; }
    public String getExerciseId() { return exerciseId; }
    public String getExerciseName() { return exerciseName; }
    public Integer getSets() { return sets; }
    public Integer getReps() { return reps; }
    public Float getWeight() { return weight; }
    public Integer getDuration() { return duration; }
    public Float getDistance() { return distance; }
    public int getOrder() { return order; }
}