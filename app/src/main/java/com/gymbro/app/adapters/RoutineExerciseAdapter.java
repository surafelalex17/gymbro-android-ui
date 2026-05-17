package com.gymbro.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.app.R;
import com.gymbro.app.models.RoutineExercise;

import java.util.ArrayList;
import java.util.List;

public class RoutineExerciseAdapter extends
        RecyclerView.Adapter<RoutineExerciseAdapter.ViewHolder> {

    private List<RoutineExercise> exercises = new ArrayList<>();

    public interface OnRemoveClickListener {
        void onRemove(RoutineExercise routineExercise);
    }

    private final OnRemoveClickListener listener;

    public RoutineExerciseAdapter(OnRemoveClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_routine_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoutineExercise re = exercises.get(position);

        if (re.getExercise() != null) {
            holder.tvName.setText(re.getExercise().getName());
            holder.tvCategory.setText(re.getExercise().getCategory());
        }

        holder.tvSetsReps.setText(re.getSets() + " sets × " + re.getReps() + " reps");

        holder.btnRemove.setOnClickListener(v -> {
            if (listener != null) listener.onRemove(re);
        });
    }

    @Override
    public int getItemCount() { return exercises.size(); }

    public void setExercises(List<RoutineExercise> list) {
        this.exercises = list;
        notifyDataSetChanged();
    }

    public void removeExercise(RoutineExercise re) {
        int index = -1;
        for (int i = 0; i < exercises.size(); i++) {
            if (exercises.get(i).getId().equals(re.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            exercises.remove(index);
            notifyItemRemoved(index);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSetsReps, tvCategory, btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName     = itemView.findViewById(R.id.tv_exercise_name);
            tvSetsReps = itemView.findViewById(R.id.tv_sets_reps);
            tvCategory = itemView.findViewById(R.id.tv_category);
            btnRemove  = itemView.findViewById(R.id.btn_remove);
        }
    }
}