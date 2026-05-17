package com.gymbro.app.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gymbro.app.R;
import com.gymbro.app.models.SessionExercise;
import com.gymbro.app.models.SessionSet;

import java.util.List;

public class SessionExerciseAdapter extends
        RecyclerView.Adapter<SessionExerciseAdapter.ViewHolder> {

    private final List<SessionExercise> exercises;
    private final Context context;

    public SessionExerciseAdapter(Context context,
                                  List<SessionExercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_session_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SessionExercise exercise = exercises.get(position);

        holder.tvName.setText(exercise.getExerciseName());
        holder.tvCategory.setText(exercise.getExerciseCategory());

        // Build set rows
        buildSetRows(holder.setsContainer, exercise, position);

        // Add Set button
        holder.btnAddSet.setOnClickListener(v -> {
            exercise.addSet();
            buildSetRows(holder.setsContainer, exercise, position);
        });
    }

    private void buildSetRows(LinearLayout container,
                              SessionExercise exercise, int exPosition) {
        container.removeAllViews();
        List<SessionSet> sets = exercise.getSets();

        for (int i = 0; i < sets.size(); i++) {
            SessionSet set = sets.get(i);
            final int setIndex = i;

            View row = LayoutInflater.from(context)
                    .inflate(R.layout.item_set_row, container, false);

            TextView tvSetNum  = row.findViewById(R.id.tv_set_number);
            EditText etWeight  = row.findViewById(R.id.et_weight);
            EditText etReps    = row.findViewById(R.id.et_reps);
            TextView btnDone   = row.findViewById(R.id.btn_done);

            tvSetNum.setText(String.valueOf(i + 1));

            // Pre-fill if values exist
            if (set.getWeight() > 0)
                etWeight.setText(String.valueOf(set.getWeight()));
            if (set.getReps() > 0)
                etReps.setText(String.valueOf(set.getReps()));

            // Update done state visually
            updateDoneState(btnDone, etWeight, etReps, set.isDone());

            // Watch weight input
            etWeight.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
                @Override public void afterTextChanged(Editable s) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        set.setWeight(Float.parseFloat(s.toString()));
                    } catch (NumberFormatException e) {
                        set.setWeight(0);
                    }
                }
            });

            // Watch reps input
            etReps.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
                @Override public void afterTextChanged(Editable s) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        set.setReps(Integer.parseInt(s.toString()));
                    } catch (NumberFormatException e) {
                        set.setReps(0);
                    }
                }
            });

            // Done toggle
            btnDone.setOnClickListener(v -> {
                set.setDone(!set.isDone());
                updateDoneState(btnDone, etWeight, etReps, set.isDone());
            });

            container.addView(row);
        }
    }

    private void updateDoneState(TextView btnDone, EditText etWeight,
                                 EditText etReps, boolean isDone) {
        if (isDone) {
            btnDone.setText("✓");
            btnDone.setTextColor(context.getColor(R.color.success));
            etWeight.setAlpha(0.6f);
            etReps.setAlpha(0.6f);
        } else {
            btnDone.setText("○");
            btnDone.setTextColor(context.getColor(R.color.text_muted));
            etWeight.setAlpha(1f);
            etReps.setAlpha(1f);
        }
    }

    @Override
    public int getItemCount() { return exercises.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCategory, btnAddSet;
        LinearLayout setsContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName       = itemView.findViewById(R.id.tv_exercise_name);
            tvCategory   = itemView.findViewById(R.id.tv_exercise_category);
            setsContainer = itemView.findViewById(R.id.sets_container);
            btnAddSet    = itemView.findViewById(R.id.btn_add_set);
        }
    }
}