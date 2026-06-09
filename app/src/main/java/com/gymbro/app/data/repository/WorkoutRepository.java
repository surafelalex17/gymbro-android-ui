package com.gymbro.app.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.gymbro.app.data.local.AppDatabase;
import com.gymbro.app.data.local.dao.WorkoutDao;
import com.gymbro.app.data.local.entities.WorkoutEntity;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.models.Workout;
import com.gymbro.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkoutRepository {

    private final WorkoutDao workoutDao;
    private final Context context;
    private final ExecutorService executor;
    private final Handler mainHandler;

    public interface WorkoutCallback {
        void onSuccess(List<Workout> workouts);
        void onError(String message);
    }

    public WorkoutRepository(Context context) {
        this.context = context;
        this.workoutDao = AppDatabase.getInstance(context).workoutDao();
        this.executor = Executors.newSingleThreadExecutor();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public void getWorkouts(WorkoutCallback callback) {
        executor.execute(() -> {
            List<WorkoutEntity> cached = workoutDao.getAll();

            if (!cached.isEmpty()) {
                mainHandler.post(() -> callback.onSuccess(toModelList(cached)));
            }

            fetchFromApi(callback, cached.isEmpty());
        });
    }

    private void fetchFromApi(WorkoutCallback callback, boolean isFirstLoad) {
        SessionManager session = new SessionManager(context);
        String token = "Bearer " + session.getToken();

        RetrofitClient.getInstance(context)
                .getApiService()
                .getWorkouts(token)
                .enqueue(new Callback<ApiResponse<List<Workout>>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<List<Workout>>> call,
                                           Response<ApiResponse<List<Workout>>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Workout> workouts = response.body().getData();

                            executor.execute(() -> {
                                workoutDao.deleteAll();
                                workoutDao.insertAll(toEntityList(workouts));
                            });

                            if (isFirstLoad) {
                                mainHandler.post(() -> callback.onSuccess(workouts));
                            } else {
                                mainHandler.post(() -> callback.onSuccess(workouts));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Workout>>> call, Throwable t) {
                        if (isFirstLoad) {
                            mainHandler.post(() -> callback.onError("Could not connect to server."));
                        }
                    }
                });
    }


    private List<Workout> toModelList(List<WorkoutEntity> entities) {
        List<Workout> list = new ArrayList<>();
        for (WorkoutEntity e : entities) list.add(toModel(e));
        return list;
    }

    private Workout toModel(WorkoutEntity e) {
        Workout w = new Workout();
        w.setId(e.getId());
        w.setUserId(e.getUserId());
        w.setTitle(e.getTitle());
        w.setDescription(e.getDescription());
        w.setType(e.getType());
        w.setDuration(e.getDuration());
        w.setCaloriesBurned(e.getCaloriesBurned());
        w.setNotes(e.getNotes());
        w.setCompletedAt(e.getCompletedAt());
        w.setCreatedAt(e.getCreatedAt());
        return w;
    }

    private List<WorkoutEntity> toEntityList(List<Workout> models) {
        List<WorkoutEntity> list = new ArrayList<>();
        for (Workout w : models) list.add(toEntity(w));
        return list;
    }

    private WorkoutEntity toEntity(Workout w) {
        return new WorkoutEntity(
                w.getId(), w.getUserId(), w.getTitle(),
                w.getDescription(), w.getType(), w.getDuration(),
                w.getCaloriesBurned(), w.getNotes(),
                w.getCompletedAt(), w.getCreatedAt()
        );
    }
    public void refreshWorkouts(WorkoutCallback callback) {
        fetchFromApi(callback, true);
    }
}