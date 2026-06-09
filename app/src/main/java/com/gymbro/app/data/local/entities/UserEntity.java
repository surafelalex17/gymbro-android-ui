package com.gymbro.app.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Float weight;
    private Float height;
    private String fitnessGoal;
    private String createdAt;

    public UserEntity(@NonNull String id, String email, String firstName,
                      String lastName, Float weight, Float height,
                      String fitnessGoal, String createdAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
        this.createdAt = createdAt;
    }

    @NonNull public String getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Float getWeight() { return weight; }
    public Float getHeight() { return height; }
    public String getFitnessGoal() { return fitnessGoal; }
    public String getCreatedAt() { return createdAt; }
}