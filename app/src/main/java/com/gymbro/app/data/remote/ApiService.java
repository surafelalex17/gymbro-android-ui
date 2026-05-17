package com.gymbro.app.data.remote;

import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.data.remote.dto.AuthRequest;
import com.gymbro.app.data.remote.dto.AuthResponse;
import com.gymbro.app.data.remote.dto.RegisterRequest;
import com.gymbro.app.data.remote.dto.RoutineExerciseRequest;
import com.gymbro.app.data.remote.dto.RoutineRequest;
import com.gymbro.app.models.Exercise;
import com.gymbro.app.models.Routine;
import com.gymbro.app.models.User;
import com.gymbro.app.models.Workout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface ApiService {

    @GET("exercises")
    Call<ApiResponse<List<Exercise>>> getExercises(@Header("Authorization") String token);

    @GET("exercises/categories")
    Call<ApiResponse<List<String>>> getExerciseCategories(@Header("Authorization") String token);


    // ── Auth ──────────────────────────────────────────────────────────────────
    @POST("auth/register")
    Call<ApiResponse<AuthResponse>> register(@Body RegisterRequest request);

    @POST("auth/login")
    Call<ApiResponse<AuthResponse>> login(@Body AuthRequest request);

    // ── Users ─────────────────────────────────────────────────────────────────
    @GET("users/profile")
    Call<ApiResponse<User>> getProfile(@Header("Authorization") String token);

    @PUT("users/profile")
    Call<ApiResponse<User>> updateProfile(
            @Header("Authorization") String token,
            @Body User user
    );

    // ── Workouts ──────────────────────────────────────────────────────────────
    @GET("workouts")
    Call<ApiResponse<List<Workout>>> getWorkouts(@Header("Authorization") String token);

    @POST("workouts")
    Call<ApiResponse<Workout>> createWorkout(
            @Header("Authorization") String token,
            @Body Workout workout
    );

    @PUT("workouts/{id}")
    Call<ApiResponse<Workout>> updateWorkout(
            @Header("Authorization") String token,
            @Path("id") String workoutId,
            @Body Workout workout
    );

    @DELETE("workouts/{id}")
    Call<ApiResponse<Void>> deleteWorkout(
            @Header("Authorization") String token,
            @Path("id") String workoutId

    );
    @GET("routines")
    Call<ApiResponse<List<Routine>>> getRoutines(@Header("Authorization") String token);

    @POST("routines")
    Call<ApiResponse<Routine>> createRoutine(
            @Header("Authorization") String token,
            @Body RoutineRequest request
    );

    @DELETE("routines/{id}")
    Call<ApiResponse<Void>> deleteRoutine(
            @Header("Authorization") String token,
            @Path("id") String routineId
    );
    @POST("routines/{id}/exercises")
    Call<ApiResponse<Object>> addExerciseToRoutine(
            @Header("Authorization") String token,
            @Path("id") String routineId,
            @Body RoutineExerciseRequest request
    );

    @DELETE("routines/{id}/exercises/{exerciseId}")
    Call<ApiResponse<Void>> removeExerciseFromRoutine(
            @Header("Authorization") String token,
            @Path("id") String routineId,
            @Path("exerciseId") String routineExerciseId
    );
    @GET("routines/{id}")
    Call<ApiResponse<Routine>> getRoutineById(
            @Header("Authorization") String token,
            @Path("id") String routineId
    );
}