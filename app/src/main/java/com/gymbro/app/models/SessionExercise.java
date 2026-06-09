package com.gymbro.app.models;

import java.util.ArrayList;
import java.util.List;

public class SessionExercise {

    private String exerciseId;
    private String exerciseName;
    private String exerciseCategory;
    private List<SessionSet> sets;

    public SessionExercise(String exerciseId, String exerciseName,
                           String exerciseCategory) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseCategory = exerciseCategory;
        this.sets = new ArrayList<>();

        sets.add(new SessionSet());
        sets.add(new SessionSet());
        sets.add(new SessionSet());
    }

    public String getExerciseId() { return exerciseId; }
    public String getExerciseName() { return exerciseName; }
    public String getExerciseCategory() { return exerciseCategory; }
    public List<SessionSet> getSets() { return sets; }
    public void addSet() { sets.add(new SessionSet()); }
    public void clearSets() {
        this.sets.clear();
    }
}