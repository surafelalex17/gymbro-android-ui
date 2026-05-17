package com.gymbro.app.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymbro.app.R;
import com.gymbro.app.utils.SessionManager;

public class SettingsActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        sessionManager = new SessionManager(this);

        // ── Back button ───────────────────────────────────────────────────────
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        // ── Fill Profile rows ─────────────────────────────────────────────────
        setupRow(
                R.id.row_profile,
                "Profile",
                sessionManager.getFirstName() + " " + "(tap to edit)"
        );

        setupRow(
                R.id.row_weight_unit,
                "Weight Unit",
                "kg"
        );

        setupRow(
                R.id.row_goal,
                "Weekly Goal",
                "3 workouts"
        );

        // ── Fill App rows ─────────────────────────────────────────────────────
        setupRow(
                R.id.row_notifications,
                "Notifications",
                "On"
        );

        setupRow(
                R.id.row_theme,
                "Theme",
                "Dark"
        );

        setupRow(
                R.id.row_rest_timer,
                "Rest Timer",
                "90 seconds"
        );

        // ── Row click listeners ───────────────────────────────────────────────
        findViewById(R.id.row_profile).setOnClickListener(v ->
                showInfoDialog("Profile", "Profile editing coming soon!")
        );

        findViewById(R.id.row_weight_unit).setOnClickListener(v ->
                showWeightUnitDialog()
        );

        findViewById(R.id.row_goal).setOnClickListener(v ->
                showGoalDialog()
        );

        findViewById(R.id.row_notifications).setOnClickListener(v ->
                showInfoDialog("Notifications", "Notification settings coming soon!")
        );

        findViewById(R.id.row_theme).setOnClickListener(v ->
                showInfoDialog("Theme", "Currently using dark theme.")
        );

        findViewById(R.id.row_rest_timer).setOnClickListener(v ->
                showInfoDialog("Rest Timer", "Rest timer settings coming soon!")
        );

        // ── Logout ────────────────────────────────────────────────────────────
        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> showLogoutDialog());
    }

    // ── Helper: fill a settings row ───────────────────────────────────────────

    private void setupRow(int rowId, String title, String value) {
        View row = findViewById(rowId);
        if (row == null) return;

        TextView tvTitle = row.findViewById(R.id.tv_setting_title);
        TextView tvValue = row.findViewById(R.id.tv_setting_value);

        if (tvTitle != null) tvTitle.setText(title);
        if (tvValue != null) tvValue.setText(value);
    }

    // ── Dialogs ───────────────────────────────────────────────────────────────

    private void showInfoDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showWeightUnitDialog() {
        String[] options = { "kg", "lbs" };
        new AlertDialog.Builder(this)
                .setTitle("Weight Unit")
                .setItems(options, (dialog, which) -> {
                    String selected = options[which];
                    setupRow(R.id.row_weight_unit, "Weight Unit", selected);
                })
                .show();
    }

    private void showGoalDialog() {
        String[] options = {
                "2 workouts", "3 workouts", "4 workouts",
                "5 workouts", "6 workouts", "7 workouts"
        };
        new AlertDialog.Builder(this)
                .setTitle("Weekly Goal")
                .setItems(options, (dialog, which) -> {
                    String selected = options[which];
                    setupRow(R.id.row_goal, "Weekly Goal", selected);
                })
                .show();
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Log Out", (dialog, which) -> logout())
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void logout() {
        sessionManager.clearSession();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}