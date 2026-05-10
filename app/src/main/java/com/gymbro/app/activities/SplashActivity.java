package com.gymbro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gymbro.app.R;
import com.gymbro.app.utils.SessionManager;

/**
 * SplashActivity
 * File: java/com/gymbro/app/activities/SplashActivity.java
 *
 * Entry point of the app. Shows logo animation then:
 * - If user is logged in → go to MainActivity
 * - If not → go to LoginActivity
 *
 * BACKEND INTEGRATION: Replace SessionManager with real auth token check.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION_MS = 1800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Animate logo and title in
        ImageView logo = findViewById(R.id.splash_logo);
        TextView title = findViewById(R.id.splash_title);
        TextView tagline = findViewById(R.id.splash_tagline);

        // Staggered fade-in animation
        logo.animate().alpha(1f).translationY(0).setDuration(500)
                .setStartDelay(100).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        title.animate().alpha(1f).setDuration(500)
                .setStartDelay(350).start();
        tagline.animate().alpha(1f).setDuration(500)
                .setStartDelay(550).start();

        // Navigate after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SessionManager session = new SessionManager(this);
            Intent intent;
            if (session.isLoggedIn()) {
                intent = new Intent(this, MainActivity.class);
            } else {
                intent = new Intent(this, LoginActivity.class);
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_DURATION_MS);
    }
}