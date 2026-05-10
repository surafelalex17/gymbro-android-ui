package com.gymbro.app.models;

/**
 * Model class representing a single exercise.
 * Structured to support future backend (Node.js) integration.
 */
public class Exercise {

    // Fields map to future API response fields
    private String id;
    private String name;
    private String muscleGroup;
    private String secondaryMuscles;
    private String equipment;
    private String difficulty;      // Beginner, Intermediate, Advanced
    private String instructions;
    private String category;        // Strength, Cardio, Stretching, etc.
    private int imageResId;         // Local drawable resource (frontend only)

    // Default constructor (needed for future JSON deserialization)
    public Exercise() {}

    // Full constructor
    public Exercise(String id, String name, String muscleGroup, String secondaryMuscles,
                    String equipment, String difficulty, String instructions, String category) {
        this.id = id;
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.secondaryMuscles = secondaryMuscles;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.instructions = instructions;
        this.category = category;
    }

    // --- Getters and Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMuscleGroup() { return muscleGroup; }
    public void setMuscleGroup(String muscleGroup) { this.muscleGroup = muscleGroup; }

    public String getSecondaryMuscles() { return secondaryMuscles; }
    public void setSecondaryMuscles(String secondaryMuscles) { this.secondaryMuscles = secondaryMuscles; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }

    /**
     * Returns a short display label for difficulty with color hint.
     */
    public String getDifficultyLabel() {
        if (difficulty == null) return "Beginner";
        return difficulty;
    }
}