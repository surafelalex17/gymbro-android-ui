package com.gymbro.app.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.app.R;
import com.gymbro.app.adapters.SessionExerciseAdapter;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.models.Exercise;
import com.gymbro.app.models.Routine;
import com.gymbro.app.models.RoutineExercise;
import com.gymbro.app.models.SessionExercise;
import com.gymbro.app.models.SessionSet;
import com.gymbro.app.models.Workout;
import com.gymbro.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkoutSessionActivity extends AppCompatActivity {

    public static final String EXTRA_ROUTINE = "extra_routine";

    private TextView tvRoutineName;
    private RecyclerView recyclerView;
    private Button btnFinishWorkout;

    private SessionExerciseAdapter adapter;
    private List<SessionExercise> sessionExercises = new ArrayList<>();
    private Routine routine;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_session);

        sessionManager = new SessionManager(this);
        routine = (Routine) getIntent().getSerializableExtra(EXTRA_ROUTINE);

        tvRoutineName    = findViewById(R.id.tv_routine_name);
        recyclerView     = findViewById(R.id.rv_session_exercises);
        btnFinishWorkout = findViewById(R.id.btn_finish_workout);

        // Setup RecyclerView first
        adapter = new SessionExerciseAdapter(this, sessionExercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (routine != null) {
            tvRoutineName.setText(routine.getName());
            // Always load fresh routine from API to get exercises
            loadRoutineWithExercises();
        }

        // Cancel — set on the whole top bar cancel area
        View topBar = findViewById(R.id.top_bar);
        TextView btnCancel = topBar.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> showCancelDialog());
        android.util.Log.d("CANCEL_DEBUG", "btnCancel null? " + (btnCancel == null));
        //findViewById(R.id.btn_finish).setOnClickListener(v -> finishWorkout());

    }

    // ── Build exercise list ───────────────────────────────────────────────────

    private void buildSessionExercises() {
        sessionExercises.clear();

        if (routine.getExercises() != null && !routine.getExercises().isEmpty()) {
            for (RoutineExercise re : routine.getExercises()) {
                // Get the nested exercise object
                Exercise ex = re.getExercise();
                if (ex != null) {
                    SessionExercise sessionEx = new SessionExercise(
                            ex.getId(),
                            ex.getName(),
                            ex.getCategory()
                    );
                    // Use the sets count from routine exercise
                    // Clear default 3 sets and add correct number
                    sessionEx.clearSets();
                    for (int i = 0; i < re.getSets(); i++) {
                        sessionEx.addSet();
                    }
                    sessionExercises.add(sessionEx);
                }
            }
            adapter.notifyDataSetChanged();

            btnFinishWorkout.setText("💾  Finish Workout");
            btnFinishWorkout.setOnClickListener(v -> finishWorkout());

        } else {
            Toast.makeText(this,
                    "No exercises yet. Tap below to add some!",
                    Toast.LENGTH_LONG).show();

            btnFinishWorkout.setText("+ Add Exercises");
            btnFinishWorkout.setOnClickListener(v -> {
                Intent intent = new Intent(this, ExercisePickerActivity.class);
                intent.putExtra(ExercisePickerActivity.EXTRA_ROUTINE_ID, routine.getId());
                startActivityForResult(intent, 100);
            });
        }
    }

    private void showAddExercisesButton() {
        btnFinishWorkout.setText("+ Add Exercises");
        btnFinishWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExercisePickerActivity.class);
            intent.putExtra(ExercisePickerActivity.EXTRA_ROUTINE_ID, routine.getId());
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            // Exercises were added — reload routine from API
            loadRoutineWithExercises();
        }
    }

    private void loadRoutineWithExercises() {
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
                            buildSessionExercises();
                        } else {
                            Toast.makeText(WorkoutSessionActivity.this,
                                    "Could not load routine", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Routine>> call, Throwable t) {
                        Toast.makeText(WorkoutSessionActivity.this,
                                "Connection error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // ── Finish Workout ────────────────────────────────────────────────────────

    private void finishWorkout() {
        // Check at least one set is done
        boolean hasCompletedSet = false;
        for (SessionExercise ex : sessionExercises) {
            for (SessionSet set : ex.getSets()) {
                if (set.isDone()) {
                    hasCompletedSet = true;
                    break;
                }
            }
        }

        if (!hasCompletedSet) {
            Toast.makeText(this,
                    "Complete at least one set before finishing.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        saveWorkout("Bearer " + sessionManager.getToken(), 30);
    }

    private void saveWorkout(String token, int duration) {
        Workout workout = new Workout();
        workout.setTitle(routine.getName());

        // Fix: check for empty string too, not just null
        String split = routine.getSplit();
        workout.setType((split != null && !split.isEmpty()) ? split : "strength");

        workout.setDuration(duration);

        String desc = routine.getDescription();
        workout.setDescription((desc != null) ? desc : "");
        workout.setNotes("Completed via " + routine.getName() + " routine");

        android.util.Log.d("WORKOUT_SAVE", "title=" + workout.getTitle()
                + " type=" + workout.getType()
                + " duration=" + workout.getDuration());

        RetrofitClient.getInstance(this)
                .getApiService()
                .createWorkout(token, workout)
                .enqueue(new Callback<ApiResponse<Workout>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<Workout>> call,
                                           Response<ApiResponse<Workout>> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(WorkoutSessionActivity.this,
                                    "Workout saved! 💪", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(
                                    WorkoutSessionActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(WorkoutSessionActivity.this,
                                    "Failed to save workout.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Workout>> call, Throwable t) {
                        Toast.makeText(WorkoutSessionActivity.this,
                                "Connection error.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // ── Cancel ────────────────────────────────────────────────────────────────

    private void showCancelDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Cancel Workout")
                .setMessage("Are you sure? Your progress will be lost.")
                .setPositiveButton("Yes, Cancel", (d, w) -> finish())
                .setNegativeButton("Keep Going", null)
                .show();

    }
}