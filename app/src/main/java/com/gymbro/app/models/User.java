package com.gymbro.app.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("weight")
    private Double weight;

    @SerializedName("height")
    private Double height;

    @SerializedName("fitnessGoal")
    private String fitnessGoal;

    @SerializedName("createdAt")
    private String createdAt;

    // Getters
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Double getWeight() { return weight; }
    public Double getHeight() { return height; }
    public String getFitnessGoal() { return fitnessGoal; }
    public String getCreatedAt() { return createdAt; }

    public Object getStatus() {
        return null;
    }
}