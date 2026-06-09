package com.gymbro.app.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.app.R;
import com.gymbro.app.adapters.RoutineExerciseAdapter;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.models.Routine;
import com.gymbro.app.models.RoutineExercise;
import com.gymbro.app.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutineDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ROUTINE = "extra_routine";

    private TextView tvName, tvDesc, btnBack, btnAddExercise;
    private Button btnStartWorkout;
    private RecyclerView recyclerView;
    private LinearLayout emptyState;

    private RoutineExerciseAdapter adapter;
    private SessionManager sessionManager;
    private Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_detail);

        sessionManager = new SessionManager(this);
        routine = (Routine) getIntent().getSerializableExtra(EXTRA_ROUTINE);

        tvName         = findViewById(R.id.tv_routine_name);
        tvDesc         = findViewById(R.id.tv_routine_desc);
        btnBack        = findViewById(R.id.btn_back);
        btnAddExercise = findViewById(R.id.btn_add_exercise);
        btnStartWorkout = findViewById(R.id.btn_start_workout);
        recyclerView   = findViewById(R.id.rv_routine_exercises);
        emptyState     = findViewById(R.id.empty_exercises);

        if (routine != null) {
            tvName.setText(routine.getName());
            tvDesc.setText(routine.getDescription() != null
                    ? routine.getDescription() : "");
        }

        adapter = new RoutineExerciseAdapter(re -> showRemoveDialog(re));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadRoutine();

        btnBack.setOnClickListener(v -> finish());

        btnAddExercise.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExercisePickerActivity.class);
            intent.putExtra(ExercisePickerActivity.EXTRA_ROUTINE_ID, routine.getId());
            startActivityForResult(intent, 100);
        });

        btnStartWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(this, WorkoutSessionActivity.class);
            intent.putExtra(WorkoutSessionActivity.EXTRA_ROUTINE, routine);
            startActivity(intent);
        });
    }

    private void loadRoutine() {
        String token = "Bearer " + sessionManager.getToken();

        RetrofitClient.getInstance(this)
                .getApiService()
                .getRoutineById(token, routine.getId())
                .enqueue(new Callback<ApiResponse<Routine>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<Routine>> call,
                                           Response<ApiResponse<Routine>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            routine = response.body().getData();
                            updateUI();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Routine>> call,
                                          Throwable t) {
                        Toast.makeText(RoutineDetailActivity.this,
                                "Could not load routine", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI() {
        if (routine.getExercises() != null && !routine.getExercises().isEmpty()) {
            adapter.setExercises(routine.getExercises());
            emptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void showRemoveDialog(RoutineExercise re) {
        String name = re.getExercise() != null
                ? re.getExercise().getName() : "this exercise";

        new AlertDialog.Builder(this)
                .setTitle("Remove Exercise")
                .setMessage("Remove " + name + " from this routine?")
                .setPositiveButton("Remove", (d, w) -> removeExercise(re))
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void removeExercise(RoutineExercise re) {
        String token = "Bearer " + sessionManager.getToken();

        RetrofitClient.getInstance(this)
                .getApiService()
                .removeExerciseFromRoutine(token, routine.getId(), re.getId())
                .enqueue(new Callback<ApiResponse<Void>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<Void>> call,
                                           Response<ApiResponse<Void>> response) {
                        if (response.isSuccessful()) {
                            adapter.removeExercise(re);
                            Toast.makeText(RoutineDetailActivity.this,
                                    "Exercise removed", Toast.LENGTH_SHORT).show();

                            // Show empty state if no exercises left
                            if (adapter.getItemCount() == 0) {
                                emptyState.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Void>> call,
                                          Throwable t) {
                        Toast.makeText(RoutineDetailActivity.this,
                                "Could not remove exercise", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            loadRoutine();
        }
    }
}