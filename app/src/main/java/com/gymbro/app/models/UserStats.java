package com.gymbro.app.models;

/**
 * Holds user profile and progress statistics.
 * Mock data for frontend; will come from backend later.
 */
public class UserStats {

    private String userId;
    private String displayName;
    private int totalWorkouts;
    private int currentStreak;      // consecutive days
    private int longestStreak;
    private float totalVolumeLifted; // in kg
    private int totalExercisesLogged;
    private String memberSince;     // e.g. "Jan 2024"
    private float bodyWeight;       // kg
    private String fitnessGoal;     // e.g. "Build Muscle", "Lose Weight"

    // --- Personal Records (mock) ---
    private float benchPressMax;
    private float squatMax;
    private float deadliftMax;
    private float overheadPressMax;

    public UserStats() {}

    // Static factory for mock data
    public static UserStats getMockStats() {
        UserStats stats = new UserStats();
        stats.userId = "user_001";
        stats.displayName = "Alex";
        stats.totalWorkouts = 47;
        stats.currentStreak = 5;
        stats.longestStreak = 14;
        stats.totalVolumeLifted = 128450f;
        stats.totalExercisesLogged = 312;
        stats.memberSince = "Jan 2024";
        stats.bodyWeight = 80f;
        stats.fitnessGoal = "Build Muscle";
        stats.benchPressMax = 100f;
        stats.squatMax = 140f;
        stats.deadliftMax = 160f;
        stats.overheadPressMax = 70f;
        return stats;
    }

    // --- Getters and Setters ---

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public int getTotalWorkouts() { return totalWorkouts; }
    public void setTotalWorkouts(int totalWorkouts) { this.totalWorkouts = totalWorkouts; }

    public int getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(int currentStreak) { this.currentStreak = currentStreak; }

    public int getLongestStreak() { return longestStreak; }
    public void setLongestStreak(int longestStreak) { this.longestStreak = longestStreak; }

    public float getTotalVolumeLifted() { return totalVolumeLifted; }
    public void setTotalVolumeLifted(float totalVolumeLifted) { this.totalVolumeLifted = totalVolumeLifted; }

    public int getTotalExercisesLogged() { return totalExercisesLogged; }
    public void setTotalExercisesLogged(int totalExercisesLogged) { this.totalExercisesLogged = totalExercisesLogged; }

    public String getMemberSince() { return memberSince; }
    public void setMemberSince(String memberSince) { this.memberSince = memberSince; }

    public float getBodyWeight() { return bodyWeight; }
    public void setBodyWeight(float bodyWeight) { this.bodyWeight = bodyWeight; }

    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }

    public float getBenchPressMax() { return benchPressMax; }
    public void setBenchPressMax(float benchPressMax) { this.benchPressMax = benchPressMax; }

    public float getSquatMax() { return squatMax; }
    public void setSquatMax(float squatMax) { this.squatMax = squatMax; }

    public float getDeadliftMax() { return deadliftMax; }
    public void setDeadliftMax(float deadliftMax) { this.deadliftMax = deadliftMax; }

    public float getOverheadPressMax() { return overheadPressMax; }
    public void setOverheadPressMax(float overheadPressMax) { this.overheadPressMax = overheadPressMax; }
}