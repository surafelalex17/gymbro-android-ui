package com.gymbro.app.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.gymbro.app.data.local.AppDatabase;
import com.gymbro.app.data.local.dao.ExerciseDao;
import com.gymbro.app.data.local.entities.ExerciseEntity;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.models.Exercise;
import com.gymbro.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseRepository {

    private final ExerciseDao exerciseDao;
    private final Context context;
    private final ExecutorService executor;
    private final Handler mainHandler;

    // Callback interface for the fragment to receive data
    public interface ExerciseCallback {
        void onSuccess(List<Exercise> exercises);
        void onError(String message);
    }

    public ExerciseRepository(Context context) {
        this.context = context;
        this.exerciseDao = AppDatabase.getInstance(context).exerciseDao();
        this.executor = Executors.newSingleThreadExecutor();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    // Main method — load from Room first, then refresh from API
    public void getExercises(ExerciseCallback callback) {
        executor.execute(() -> {
            // Step 1 — Load from Room instantly
            List<ExerciseEntity> cached = exerciseDao.getAll();

            if (!cached.isEmpty()) {
                // Return cached data immediately
                List<Exercise> exercises = toModelList(cached);
                mainHandler.post(() -> callback.onSuccess(exercises));
            }

            // Step 2 — Refresh from API in background
            fetchFromApi(callback, cached.isEmpty());
        });
    }

    // Search locally in Room (fast, no API needed)
    public void searchExercises(String query, ExerciseCallback callback) {
        executor.execute(() -> {
            List<ExerciseEntity> results = exerciseDao.search(query);
            List<Exercise> exercises = toModelList(results);
            mainHandler.post(() -> callback.onSuccess(exercises));
        });
    }

    // Filter by category locally
    public void filterByCategory(String category, ExerciseCallback callback) {
        executor.execute(() -> {
            List<ExerciseEntity> results = category.equals("all")
                    ? exerciseDao.getAll()
                    : exerciseDao.getByCategory(category);
            List<Exercise> exercises = toModelList(results);
            mainHandler.post(() -> callback.onSuccess(exercises));
        });
    }

    // Get categories from Room
    public void getCategories(ExerciseCallback callback) {
        executor.execute(() -> {
            List<String> categories = exerciseDao.getCategories();
            mainHandler.post(() -> callback.onSuccess(null));
        });
    }

    private void fetchFromApi(ExerciseCallback callback, boolean isFirstLoad) {
        SessionManager session = new SessionManager(context);
        String token = "Bearer " + session.getToken();

        RetrofitClient.getInstance(context)
                .getApiService()
                .getExercises(token)
                .enqueue(new Callback<ApiResponse<List<Exercise>>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<List<Exercise>>> call,
                                           Response<ApiResponse<List<Exercise>>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Exercise> exercises = response.body().getData();

                            // Save to Room on background thread
                            executor.execute(() -> {
                                exerciseDao.deleteAll();
                                exerciseDao.insertAll(toEntityList(exercises));
                            });

                            // If Room was empty, deliver API data now
                            if (isFirstLoad) {
                                mainHandler.post(() -> callback.onSuccess(exercises));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Exercise>>> call, Throwable t) {
                        if (isFirstLoad) {
                            mainHandler.post(() -> callback.onError("Could not connect to server."));
                        }
                    }
                });
    }

    // Convert Entity → Model
    private List<Exercise> toModelList(List<ExerciseEntity> entities) {
        List<Exercise> list = new ArrayList<>();
        for (ExerciseEntity e : entities) {
            list.add(toModel(e));
        }
        return list;
    }

    private Exercise toModel(ExerciseEntity e) {
        Exercise ex = new Exercise();
        ex.setId(e.getId());
        ex.setName(e.getName());
        ex.setCategory(e.getCategory());
        ex.setEquipment(e.getEquipment());
        ex.setDescription(e.getDescription());
        // Convert comma string back to list
        if (e.getMuscleGroups() != null && !e.getMuscleGroups().isEmpty()) {
            ex.setMuscleGroups(List.of(e.getMuscleGroups().split(",")));
        }
        return ex;
    }

    // Convert Model → Entity
    private List<ExerciseEntity> toEntityList(List<Exercise> models) {
        List<ExerciseEntity> list = new ArrayList<>();
        for (Exercise e : models) {
            list.add(toEntity(e));
        }
        return list;
    }

    private ExerciseEntity toEntity(Exercise e) {
        String muscles = e.getMuscleGroups() != null
                ? String.join(",", e.getMuscleGroups())
                : "";
        return new ExerciseEntity(
                e.getId(), e.getName(), e.getCategory(),
                muscles, e.getEquipment(), e.getDescription()
        );
    }
}