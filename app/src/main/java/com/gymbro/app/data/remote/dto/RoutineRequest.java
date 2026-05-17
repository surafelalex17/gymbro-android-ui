package com.gymbro.app.data.remote.dto;

public class RoutineRequest {
    private String name;
    private String description;
    private String split;

    public RoutineRequest(String name, String description, String split) {
        this.name = name;
        this.description = description;
        this.split = split;
    }
}