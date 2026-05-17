package com.gymbro.app.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.io.Serializable;

public class Exercise implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("muscleGroups")
    private List<String> muscleGroups;

    @SerializedName("equipment")
    private String equipment;

    @SerializedName("description")
    private String description;

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public List<String> getMuscleGroups() { return muscleGroups; }
    public String getEquipment() { return equipment; }
    public String getDescription() { return description; }
    // Add these setters to your existing Exercise.java
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setMuscleGroups(List<String> muscleGroups) { this.muscleGroups = muscleGroups; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
    public void setDescription(String description) { this.description = description; }
}