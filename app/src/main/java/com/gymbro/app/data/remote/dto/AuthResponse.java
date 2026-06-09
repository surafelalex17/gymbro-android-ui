package com.gymbro.app.data.remote.dto;

import com.google.gson.annotations.SerializedName;
import com.gymbro.app.models.User;

public class AuthResponse {

    @SerializedName("user")
    private User user;

    @SerializedName("token")
    private String token;

    public User getUser() { return user; }
    public String getToken() { return token; }
}