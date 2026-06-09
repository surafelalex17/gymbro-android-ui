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

        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());



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



        findViewById(R.id.row_notifications).setOnClickListener(v ->
                showInfoDialog("Notifications", "Notification settings coming soon!")
        );

        findViewById(R.id.row_theme).setOnClickListener(v ->
                showInfoDialog("Theme", "Currently using dark theme.")
        );

        findViewById(R.id.row_rest_timer).setOnClickListener(v ->
                showInfoDialog("Rest Timer", "Rest timer settings coming soon!")
        );

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> showLogoutDialog());
    }


    private void setupRow(int rowId, String title, String value) {
        View row = findViewById(rowId);
        if (row == null) return;

        TextView tvTitle = row.findViewById(R.id.tv_setting_title);
        TextView tvValue = row.findViewById(R.id.tv_setting_value);

        if (tvTitle != null) tvTitle.setText(title);
        if (tvValue != null) tvValue.setText(value);
    }


    private void showInfoDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
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