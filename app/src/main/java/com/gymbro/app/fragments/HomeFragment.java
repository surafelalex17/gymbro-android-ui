package com.gymbro.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.gymbro.app.R;
import com.gymbro.app.activities.MainActivity;
import com.gymbro.app.data.repository.WorkoutRepository;
import com.gymbro.app.models.Workout;
import com.gymbro.app.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    // Header
    private TextView tvGreeting, tvUsername, tvAvatarInitial;

    // Streak
    private TextView tvStreakCount;
    private View dotMon, dotTue, dotWed, dotThu, dotFri, dotSat, dotSun;

    // Weekly goal
    private TextView tvGoalRatio, tvGoalCaption;
    private ProgressBar progressWeeklyGoal;

    // Stats
    private TextView tvWorkoutsCompleted, tvTotalDuration, tvTotalVolume;



    private SessionManager sessionManager;
    private WorkoutRepository workoutRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);
        sessionManager = new SessionManager(requireContext());
        workoutRepository = new WorkoutRepository(requireContext());

        TextView btnMenu = view.findViewById(R.id.btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).openDrawer();
                }
            });
        }

        setupHeader();
        loadWorkoutData();

        return view;
    }

    private void initViews(View view) {
        tvGreeting          = view.findViewById(R.id.tv_greeting);
        tvUsername          = view.findViewById(R.id.tv_username);
        tvStreakCount       = view.findViewById(R.id.tv_streak_count);
        dotMon              = view.findViewById(R.id.dot_mon);
        dotTue              = view.findViewById(R.id.dot_tue);
        dotWed              = view.findViewById(R.id.dot_wed);
        dotThu              = view.findViewById(R.id.dot_thu);
        dotFri              = view.findViewById(R.id.dot_fri);
        dotSat              = view.findViewById(R.id.dot_sat);
        dotSun              = view.findViewById(R.id.dot_sun);
        tvGoalRatio         = view.findViewById(R.id.tv_goal_ratio);
        tvGoalCaption       = view.findViewById(R.id.tv_goal_caption);
        progressWeeklyGoal  = view.findViewById(R.id.progress_weekly_goal);
        tvWorkoutsCompleted = view.findViewById(R.id.tv_workouts_completed);
        tvTotalDuration     = view.findViewById(R.id.tv_total_duration);
        tvTotalVolume       = view.findViewById(R.id.tv_total_volume);

    }

    // ── Header ────────────────────────────────────────────────────────────────

    private void setupHeader() {
        // Greeting based on time of day
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String greeting;
        if (hour < 12)       greeting = "Good morning 👋";
        else if (hour < 17)  greeting = "Good afternoon 👋";
        else                 greeting = "Good evening 👋";

        tvGreeting.setText(greeting);

        // User name from session
        String firstName = sessionManager.getFirstName();
        if (firstName != null && !firstName.isEmpty()) {
            tvUsername.setText(firstName);

        }
    }

    // ── Workout Data ──────────────────────────────────────────────────────────

    private void loadWorkoutData() {
        workoutRepository.getWorkouts(new WorkoutRepository.WorkoutCallback() {
            @Override
            public void onSuccess(List<Workout> workouts) {
                if (getActivity() == null) return;
                requireActivity().runOnUiThread(() -> {
                    updateStats(workouts);
                    updateStreak(workouts);
                    updateWeeklyGoal(workouts);
                });
            }

            @Override
            public void onError(String message) {
                // Show placeholder data if offline
                showPlaceholders();
            }

        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (workoutRepository != null) {
            // Use getWorkouts instead of refreshWorkouts
            // Room responds instantly, API updates in background
            workoutRepository.getWorkouts(
                    new WorkoutRepository.WorkoutCallback() {
                        @Override
                        public void onSuccess(List<Workout> workouts) {
                            if (getActivity() == null) return;
                            requireActivity().runOnUiThread(() -> {
                                updateStats(workouts);
                                updateStreak(workouts);
                                updateWeeklyGoal(workouts);
                            });
                        }
                        @Override
                        public void onError(String message) {
                            showPlaceholders();
                        }
                    });
        }
    }

    // ── Stats ─────────────────────────────────────────────────────────────────

    private void updateStats(List<Workout> workouts) {
        List<Workout> thisWeek = getThisWeekWorkouts(workouts);

        // Workouts completed
        tvWorkoutsCompleted.setText(String.valueOf(thisWeek.size()));

        // Total duration
        int totalMinutes = 0;
        for (Workout w : thisWeek) totalMinutes += w.getDuration();

        if (totalMinutes >= 60) {
            int hours = totalMinutes / 60;
            int mins  = totalMinutes % 60;
            tvTotalDuration.setText(hours + "h " + mins + "m");
        } else {
            tvTotalDuration.setText(totalMinutes + "m");
        }

        // Total volume placeholder
        tvTotalVolume.setText("—");
    }

    // ── Streak Dots ───────────────────────────────────────────────────────────

    private void updateStreak(List<Workout> workouts) {
        // Get which days of this week had workouts
        boolean[] workedOut = getWorkoutDaysThisWeek(workouts);

        // Mon=0 Tue=1 Wed=2 Thu=3 Fri=4 Sat=5 Sun=6
        View[] dots = { dotMon, dotTue, dotWed, dotThu, dotFri, dotSat, dotSun };

        int streakCount = 0;
        for (int i = 0; i < 7; i++) {
            if (workedOut[i]) {
                dots[i].setBackgroundResource(R.drawable.bg_day_dot_active);
                streakCount++;
            } else {
                dots[i].setBackgroundResource(R.drawable.bg_day_dot_inactive);
            }
        }

        tvStreakCount.setText(streakCount + " days");
    }

    // ── Weekly Goal ───────────────────────────────────────────────────────────

    private void updateWeeklyGoal(List<Workout> workouts) {
        int weeklyGoal = 3; // default — later load from UserSettings
        int completed  = getThisWeekWorkouts(workouts).size();
        int capped     = Math.min(completed, weeklyGoal);

        progressWeeklyGoal.setMax(weeklyGoal);
        progressWeeklyGoal.setProgress(capped);
        tvGoalRatio.setText(completed + " / " + weeklyGoal + " workouts");

        if (completed >= weeklyGoal) {
            tvGoalCaption.setText("🎯 Weekly goal smashed!");
            tvGoalCaption.setTextColor(
                    requireContext().getColor(R.color.success));
        } else {
            int remaining = weeklyGoal - completed;
            tvGoalCaption.setText("💪 " + remaining + " more to hit your goal");
            tvGoalCaption.setTextColor(
                    requireContext().getColor(R.color.text_secondary));
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    // Returns only workouts from this week (Mon–Sun)
    private List<Workout> getThisWeekWorkouts(List<Workout> all) {
        List<Workout> thisWeek = new java.util.ArrayList<>();

        // Fix: explicitly set start of THIS week correctly
        Calendar startOfWeek = Calendar.getInstance();
        // Go back to find this Monday
        while (startOfWeek.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            startOfWeek.add(Calendar.DAY_OF_MONTH, -1);
        }
        startOfWeek.set(Calendar.HOUR_OF_DAY, 0);
        startOfWeek.set(Calendar.MINUTE, 0);
        startOfWeek.set(Calendar.SECOND, 0);
        startOfWeek.set(Calendar.MILLISECOND, 0);

        android.util.Log.d("HOME_DEBUG", "Start of week: " + startOfWeek.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC")); // Fix timezone

        for (Workout w : all) {
            try {
                Date date = sdf.parse(w.getCompletedAt());
                android.util.Log.d("HOME_DEBUG", "Workout: " + w.getTitle()
                        + " date: " + date
                        + " after: " + (date != null && date.after(startOfWeek.getTime())));
                if (date != null && date.after(startOfWeek.getTime())) {
                    thisWeek.add(w);
                }
            } catch (Exception e) {
                android.util.Log.e("HOME_DEBUG", "Parse error: " + e.getMessage());
            }
        }
        return thisWeek;
    }

    // Returns boolean[7] — true if user worked out that day this week
    private boolean[] getWorkoutDaysThisWeek(List<Workout> all) {
        boolean[] days = new boolean[7];

        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC")); // Fix timezone

        Calendar startOfWeek = Calendar.getInstance();
        while (startOfWeek.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            startOfWeek.add(Calendar.DAY_OF_MONTH, -1);
        }
        startOfWeek.set(Calendar.HOUR_OF_DAY, 0);
        startOfWeek.set(Calendar.MINUTE, 0);
        startOfWeek.set(Calendar.SECOND, 0);
        startOfWeek.set(Calendar.MILLISECOND, 0);

        for (Workout w : all) {
            try {
                Date date = sdf.parse(w.getCompletedAt());
                if (date == null) continue;

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                if (date.after(startOfWeek.getTime())) {
                    int day = cal.get(Calendar.DAY_OF_WEEK);
                    int index = (day == Calendar.SUNDAY) ? 6 : day - Calendar.MONDAY;
                    if (index >= 0 && index < 7) days[index] = true;
                }
            } catch (Exception e) {
                // skip
            }
        }
        return days;
    }

    private void showPlaceholders() {
        tvWorkoutsCompleted.setText("0");
        tvTotalDuration.setText("0m");
        tvTotalVolume.setText("—");
        tvStreakCount.setText("0 days");
        tvGoalRatio.setText("0 / 3 workouts");
        tvGoalCaption.setText("💪 Log your first workout!");
        progressWeeklyGoal.setProgress(0);
    }
}
