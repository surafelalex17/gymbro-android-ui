package com.gymbro.app.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.app.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import com.gymbro.app.activities.RoutineDetailActivity;
import com.gymbro.app.activities.SettingsActivity;
import com.gymbro.app.activities.WorkoutSessionActivity;
import com.gymbro.app.adapters.RoutineAdapter;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.data.remote.dto.RoutineRequest;
import com.gymbro.app.data.repository.RoutineRepository;
import com.gymbro.app.models.Routine;
import com.gymbro.app.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutinesFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout emptyState;
    private TextView tvUserName;
    private ExtendedFloatingActionButton fabAdd;


    private RoutineAdapter adapter;
    private SessionManager sessionManager;

    private RoutineRepository routineRepository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routines, container, false);

        // Wire up views
        recyclerView = view.findViewById(R.id.rv_routines);
        emptyState   = view.findViewById(R.id.empty_routines);
        tvUserName   = view.findViewById(R.id.tv_user_name);
        fabAdd       = view.findViewById(R.id.fab_add_routine);


        sessionManager = new SessionManager(requireContext());
        routineRepository = new RoutineRepository(requireContext());


        // Set user name
        String name = sessionManager.getFirstName();
        if (name != null) tvUserName.setText(name);

        // Setup RecyclerView
        adapter = new RoutineAdapter(new RoutineAdapter.OnRoutineClickListener() {
            @Override
            public void onRoutineClick(Routine routine) {
                Intent intent = new Intent(requireContext(),
                        RoutineDetailActivity.class);
                intent.putExtra(RoutineDetailActivity.EXTRA_ROUTINE, routine);
                startActivity(intent);
            }

            @Override
            public void onRoutineLongClick(Routine routine) {
                showDeleteDialog(routine);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        // Load routines
        loadRoutines();

        // FAB — show add dialog
        fabAdd.setOnClickListener(v -> showAddRoutineDialog());

        // Settings


        return view;
    }

    // ── Load Routines ─────────────────────────────────────────────────────────

    private void loadRoutines() {
        routineRepository.getRoutines(
                new RoutineRepository.RoutineCallback() {
                    @Override
                    public void onSuccess(List<Routine> routines) {
                        if (getActivity() == null) return;
                        requireActivity().runOnUiThread(() -> {
                            adapter.setRoutines(routines);
                            showEmptyState(routines.isEmpty());
                        });
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(getContext(),
                                "Could not load routines",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // ── Add Routine Dialog ────────────────────────────────────────────────────

    private void showAddRoutineDialog() {
        View dialogView = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_add_routine, null);

        EditText etName  = dialogView.findViewById(R.id.et_routine_name);
        EditText etDesc  = dialogView.findViewById(R.id.et_routine_desc);
        EditText etSplit = dialogView.findViewById(R.id.et_routine_split);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnCreate = dialogView.findViewById(R.id.btn_create);

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setCancelable(true)
                .create();

        // Rounded dialog background
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent
            );
        }

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnCreate.setOnClickListener(v -> {
            String name  = etName.getText().toString().trim();
            String desc  = etDesc.getText().toString().trim();
            String split = etSplit.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Routine name is required");
                return;
            }

            createRoutine(name, desc, split, dialog);
        });

        dialog.show();
    }

    // ── Create Routine API Call ───────────────────────────────────────────────

    private void createRoutine(String name, String desc,
                               String split, AlertDialog dialog) {
        String token = "Bearer " + sessionManager.getToken();
        RoutineRequest request = new RoutineRequest(name, desc, split);

        RetrofitClient.getInstance(requireContext())
                .getApiService()
                .createRoutine(token, request)
                .enqueue(new Callback<ApiResponse<Routine>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<Routine>> call,
                                           Response<ApiResponse<Routine>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Routine newRoutine = response.body().getData();
                            adapter.addRoutine(newRoutine);
                            showEmptyState(false);
                            dialog.dismiss();
                            Toast.makeText(getContext(),
                                    "Routine created!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(),
                                    "Failed to create routine", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Routine>> call,
                                          Throwable t) {
                        Toast.makeText(getContext(),
                                "Connection error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // ── Delete Routine ────────────────────────────────────────────────────────

    private void showDeleteDialog(Routine routine) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Routine")
                .setMessage("Delete \"" + routine.getName() + "\"?")
                .setPositiveButton("Delete", (dialog, which) ->
                        deleteRoutine(routine))
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteRoutine(Routine routine) {
        String token = "Bearer " + sessionManager.getToken();

        RetrofitClient.getInstance(requireContext())
                .getApiService()
                .deleteRoutine(token, routine.getId())
                .enqueue(new Callback<ApiResponse<Void>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<Void>> call,
                                           Response<ApiResponse<Void>> response) {
                        if (response.isSuccessful()) {
                            adapter.removeRoutine(routine);
                            showEmptyState(adapter.getItemCount() == 0);
                            Toast.makeText(getContext(),
                                    "Routine deleted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Void>> call,
                                          Throwable t) {
                        Toast.makeText(getContext(),
                                "Could not delete routine", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void showEmptyState(boolean isEmpty) {
        emptyState.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}