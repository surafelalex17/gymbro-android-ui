package com.gymbro.app.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.app.R;
import com.gymbro.app.adapters.ExerciseAdapter;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.data.remote.dto.RoutineExerciseRequest;
import com.gymbro.app.models.Exercise;
import com.gymbro.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExercisePickerActivity extends AppCompatActivity {

    public static final String EXTRA_ROUTINE_ID = "extra_routine_id";

    private RecyclerView recyclerView;
    private EditText etSearch;
    private ExerciseAdapter adapter;
    private SessionManager sessionManager;

    private List<Exercise> allExercises = new ArrayList<>();
    private String routineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_picker);

        sessionManager = new SessionManager(this);
        routineId = getIntent().getStringExtra(EXTRA_ROUTINE_ID);

        // Wire views
        recyclerView = findViewById(R.id.rv_exercises);
        etSearch     = findViewById(R.id.et_search);
        TextView btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> finish());

        // Setup adapter
        adapter = new ExerciseAdapter(exercise -> addExerciseToRoutine(exercise));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load exercises
        loadExercises();

        // Search
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterExercises(s.toString());
            }
        });
    }

    private void loadExercises() {
        String token = "Bearer " + sessionManager.getToken();

        RetrofitClient.getInstance(this)
                .getApiService()
                .getExercises(token)
                .enqueue(new Callback<ApiResponse<List<Exercise>>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<List<Exercise>>> call,
                                           Response<ApiResponse<List<Exercise>>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            allExercises = response.body().getData();
                            adapter.setExercises(allExercises);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Exercise>>> call,
                                          Throwable t) {
                        Toast.makeText(ExercisePickerActivity.this,
                                "Could not load exercises", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void filterExercises(String query) {
        if (query.isEmpty()) {
            adapter.setExercises(allExercises);
            return;
        }
        List<Exercise> filtered = new ArrayList<>();
        for (Exercise e : allExercises) {
            if (e.getName().toLowerCase().contains(query.toLowerCase()) ||
                    e.getCategory().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(e);
            }
        }
        adapter.setExercises(filtered);
    }

    private void addExerciseToRoutine(Exercise exercise) {
        String token = "Bearer " + sessionManager.getToken();

        RoutineExerciseRequest request = new RoutineExerciseRequest(
                exercise.getId(), 3, 10, null
        );

        RetrofitClient.getInstance(this)
                .getApiService()
                .addExerciseToRoutine(token, routineId, request)
                .enqueue(new Callback<ApiResponse<Object>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<Object>> call,
                                           Response<ApiResponse<Object>> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ExercisePickerActivity.this,
                                    exercise.getName() + " added! ✓",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ExercisePickerActivity.this,
                                    "Failed to add exercise",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Object>> call,
                                          Throwable t) {
                        Toast.makeText(ExercisePickerActivity.this,
                                "Connection error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}