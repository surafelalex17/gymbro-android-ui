package com.gymbro.app.data.remote.dto;

import com.google.gson.annotations.SerializedName;

// This wraps every response from your API:
// { statusCode, data, message, success }
public class ApiResponse<T> {

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("data")
    private T data;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private boolean success;

    public int getStatusCode() { return statusCode; }
    public T getData() { return data; }
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
}