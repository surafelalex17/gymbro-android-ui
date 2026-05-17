package com.gymbro.app.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gymbro.app.R;
import com.gymbro.app.adapters.ExerciseAdapter;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.data.repository.ExerciseRepository;
import com.gymbro.app.models.Exercise;
import com.gymbro.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExercisesFragment extends Fragment {

    private EditText searchInput;
    private RecyclerView recyclerView;
    private TextView tvCount, emptyState;
    private LinearLayout filterChipsContainer;

    private ExerciseAdapter adapter;
    private SessionManager sessionManager;

    private List<Exercise> allExercises = new ArrayList<>();
    private String activeCategory = "all";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        // Wire up views
        searchInput          = view.findViewById(R.id.ex_search);
        recyclerView         = view.findViewById(R.id.rv_exercises);
        tvCount              = view.findViewById(R.id.tv_exercise_count);
        filterChipsContainer = view.findViewById(R.id.filter_chips_container);

        // Empty state views
        LinearLayout emptyLayout = view.findViewById(R.id.empty_state);

        sessionManager = new SessionManager(requireContext());

        // Setup RecyclerView
        adapter = new ExerciseAdapter(exercise -> {
            // Handle exercise click — show details later
            Toast.makeText(getContext(), exercise.getName(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Load all exercises from API
        loadExercises();

        // Search — filter as user types
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterExercises(s.toString(), activeCategory);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    private void loadExercises() {
        ExerciseRepository repository = new ExerciseRepository(requireContext());

        repository.getExercises(new ExerciseRepository.ExerciseCallback() {
            @Override
            public void onSuccess(List<Exercise> exercises) {
                allExercises = exercises;
                adapter.setExercises(exercises);
                tvCount.setText(exercises.size() + " exercises");
                buildCategoryChips();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildCategoryChips() {
        List<String> categories = new ArrayList<>();
        for (Exercise e : allExercises) {
            if (!categories.contains(e.getCategory())) {
                categories.add(e.getCategory());
            }
        }
        buildFilterChips(categories);
    }


        private void buildFilterChips(List<String> categories) {
        filterChipsContainer.removeAllViews();

        // Add "All" chip first
        addChip("all", "All");

        for (String category : categories) {
            // Capitalize first letter
            String label = category.substring(0, 1).toUpperCase() + category.substring(1);
            addChip(category, label);
        }
    }

    private void addChip(String category, String label) {
        TextView chip = new TextView(getContext());

        // Layout params with margin
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMarginEnd(8);
        chip.setLayoutParams(params);

        chip.setText(label);
        chip.setTextSize(13f);
        chip.setPadding(28, 16, 28, 16);
        chip.setBackground(requireContext().getDrawable(
                category.equals(activeCategory)
                        ? R.drawable.bg_primary_button
                        : R.drawable.bg_stat_card
        ));
        chip.setTextColor(requireContext().getColor(
                category.equals(activeCategory)
                        ? R.color.text_primary
                        : R.color.text_secondary
        ));

        chip.setOnClickListener(v -> {
            activeCategory = category;
            String searchQuery = searchInput.getText().toString();
            filterExercises(searchQuery, activeCategory);
            buildFilterChips(getCategoriesFromExercises());
        });

        filterChipsContainer.addView(chip);
    }

    private void filterExercises(String search, String category) {
        List<Exercise> filtered = new ArrayList<>();

        for (Exercise e : allExercises) {
            boolean matchesSearch = search.isEmpty() ||
                    e.getName().toLowerCase().contains(search.toLowerCase()) ||
                    e.getCategory().toLowerCase().contains(search.toLowerCase());

            boolean matchesCategory = category.equals("all") ||
                    e.getCategory().equalsIgnoreCase(category);

            if (matchesSearch && matchesCategory) {
                filtered.add(e);
            }
        }

        adapter.setExercises(filtered);
        tvCount.setText(filtered.size() + " exercises");

        // Show/hide empty state
        View emptyLayout = getView() != null ?
                getView().findViewById(R.id.empty_state) : null;
        if (emptyLayout != null) {
            emptyLayout.setVisibility(filtered.isEmpty() ? View.VISIBLE : View.GONE);
            recyclerView.setVisibility(filtered.isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    private List<String> getCategoriesFromExercises() {
        List<String> categories = new ArrayList<>();
        for (Exercise e : allExercises) {
            if (!categories.contains(e.getCategory())) {
                categories.add(e.getCategory());
            }
        }
        return categories;
    }
}