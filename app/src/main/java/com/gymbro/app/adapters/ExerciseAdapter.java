package com.gymbro.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gymbro.app.R;
import com.gymbro.app.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private List<Exercise> exercises = new ArrayList<>();

    // Called when user clicks an exercise
    public interface OnExerciseClickListener {
        void onExerciseClick(Exercise exercise);
    }

    private OnExerciseClickListener listener;

    public ExerciseAdapter(OnExerciseClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);

        holder.tvName.setText(exercise.getName());
        holder.tvCategory.setText(exercise.getCategory());

        // Join muscle groups with comma
        if (exercise.getMuscleGroups() != null && !exercise.getMuscleGroups().isEmpty()) {
            holder.tvMuscles.setText(String.join(" · ", exercise.getMuscleGroups()));
        }

        // Equipment
        if (exercise.getEquipment() != null) {
            holder.tvEquipment.setText("🏋 " + exercise.getEquipment());
        } else {
            holder.tvEquipment.setText("🏋 bodyweight");
        }

        // Click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onExerciseClick(exercise);
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    // Call this to update the list
    public void setExercises(List<Exercise> newExercises) {
        this.exercises = newExercises;
        notifyDataSetChanged();
    }

    // Call this to filter locally
    public void filter(String query, List<Exercise> allExercises) {
        if (query.isEmpty()) {
            setExercises(allExercises);
            return;
        }
        List<Exercise> filtered = new ArrayList<>();
        String lower = query.toLowerCase();
        for (Exercise e : allExercises) {
            if (e.getName().toLowerCase().contains(lower) ||
                    e.getCategory().toLowerCase().contains(lower)) {
                filtered.add(e);
            }
        }
        setExercises(filtered);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCategory, tvMuscles, tvEquipment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName      = itemView.findViewById(R.id.tv_exercise_name);
            tvCategory  = itemView.findViewById(R.id.tv_category);
            tvMuscles   = itemView.findViewById(R.id.tv_muscles);
            tvEquipment = itemView.findViewById(R.id.tv_equipment);
        }
    }
}