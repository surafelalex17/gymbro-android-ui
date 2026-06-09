package com.gymbro.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gymbro.app.R;
import com.gymbro.app.models.Routine;

import java.util.ArrayList;
import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    private List<Routine> routines = new ArrayList<>();

    public interface OnRoutineClickListener {
        void onRoutineClick(Routine routine);
        void onRoutineLongClick(Routine routine);
    }

    private final OnRoutineClickListener listener;

    public RoutineAdapter(OnRoutineClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_routine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Routine routine = routines.get(position);

        if (routine.getName() != null && !routine.getName().isEmpty()) {
            holder.tvInitial.setText(
                    String.valueOf(routine.getName().charAt(0)).toUpperCase()
            );
        }

        holder.tvName.setText(routine.getName());

        if (routine.getDescription() != null && !routine.getDescription().isEmpty()) {
            holder.tvDesc.setText(routine.getDescription());
        } else if (routine.getSplit() != null) {
            holder.tvDesc.setText(routine.getSplit());
        } else {
            holder.tvDesc.setText("No description");
        }

        int count = routine.getExerciseCount();
        holder.tvExerciseCount.setText(count + " exercise" + (count == 1 ? "" : "s"));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onRoutineClick(routine);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onRoutineLongClick(routine);
            return true;
        });
    }

    @Override
    public int getItemCount() { return routines.size(); }

    public void setRoutines(List<Routine> newRoutines) {
        this.routines = newRoutines;
        notifyDataSetChanged();
    }

    public void addRoutine(Routine routine) {
        routines.add(0, routine);
        notifyItemInserted(0);
    }

    public void removeRoutine(Routine routine) {
        int index = -1;
        for (int i = 0; i < routines.size(); i++) {
            if (routines.get(i).getId().equals(routine.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            routines.remove(index);
            notifyItemRemoved(index);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInitial, tvName, tvDesc, tvExerciseCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInitial       = itemView.findViewById(R.id.tv_routine_initial);
            tvName          = itemView.findViewById(R.id.tv_routine_name);
            tvDesc          = itemView.findViewById(R.id.tv_routine_desc);
            tvExerciseCount = itemView.findViewById(R.id.tv_exercise_count);
        }
    }
}