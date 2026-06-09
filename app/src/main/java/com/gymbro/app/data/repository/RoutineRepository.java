package com.gymbro.app.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.gymbro.app.data.local.AppDatabase;
import com.gymbro.app.data.local.dao.RoutineDao;
import com.gymbro.app.data.local.entities.RoutineEntity;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.models.Routine;
import com.gymbro.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutineRepository {

    private final RoutineDao routineDao;
    private final Context context;
    private final ExecutorService executor;
    private final Handler mainHandler;

    public interface RoutineCallback {
        void onSuccess(List<Routine> routines);
        void onError(String message);
    }

    public RoutineRepository(Context context) {
        this.context = context;
        this.routineDao = AppDatabase.getInstance(context).routineDao();
        this.executor   = Executors.newSingleThreadExecutor();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public void getRoutines(RoutineCallback callback) {
        executor.execute(() -> {
            // Step 1 — Return cached data instantly
            List<RoutineEntity> cached = routineDao.getAll();
            if (!cached.isEmpty()) {
                mainHandler.post(() ->
                        callback.onSuccess(toModelList(cached)));
            }

            // Step 2 — Refresh from API in background
            fetchFromApi(callback, cached.isEmpty());
        });
    }

    private void fetchFromApi(RoutineCallback callback,
                              boolean isFirstLoad) {
        SessionManager session = new SessionManager(context);
        String token = "Bearer " + session.getToken();

        RetrofitClient.getInstance(context)
                .getApiService()
                .getRoutines(token)
                .enqueue(new Callback<ApiResponse<List<Routine>>>() {

                    @Override
                    public void onResponse(
                            Call<ApiResponse<List<Routine>>> call,
                            Response<ApiResponse<List<Routine>>> response) {
                        if (response.isSuccessful()
                                && response.body() != null) {
                            List<Routine> routines =
                                    response.body().getData();

                            // Save to Room
                            executor.execute(() -> {
                                routineDao.deleteAll();
                                routineDao.insertAll(toEntityList(routines));
                            });

                            // Always update UI with fresh data
                            mainHandler.post(() ->
                                    callback.onSuccess(routines));
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<ApiResponse<List<Routine>>> call,
                            Throwable t) {
                        if (isFirstLoad) {
                            mainHandler.post(() ->
                                    callback.onError("Connection failed"));
                        }
                    }
                });
    }

    // ── Converters ────────────────────────────────────────────────────────────

    private List<Routine> toModelList(List<RoutineEntity> entities) {
        List<Routine> list = new ArrayList<>();
        for (RoutineEntity e : entities) list.add(toModel(e));
        return list;
    }

    private Routine toModel(RoutineEntity e) {
        Routine r = new Routine();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setDescription(e.getDescription());
        r.setSplit(e.getSplit());
        r.setDefault(e.isDefault());
        r.setCreatedAt(e.getCreatedAt());
        return r;
    }

    private List<RoutineEntity> toEntityList(List<Routine> models) {
        List<RoutineEntity> list = new ArrayList<>();
        for (Routine r : models) list.add(toEntity(r));
        return list;
    }

    private RoutineEntity toEntity(Routine r) {
        return new RoutineEntity(
                r.getId(),
                new com.gymbro.app.utils.SessionManager(context).getUserId(),
                r.getName(),
                r.getDescription() != null ? r.getDescription() : "",
                r.getSplit() != null ? r.getSplit() : "",
                r.isDefault(),
                r.getCreatedAt() != null ? r.getCreatedAt() : ""
        );
    }
}