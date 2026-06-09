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


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION_MS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.splash_logo);
        TextView title = findViewById(R.id.splash_title);
        TextView tagline = findViewById(R.id.splash_tagline);

        logo.animate().alpha(1f).translationY(0).setDuration(500)
                .setStartDelay(100).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        title.animate().alpha(1f).setDuration(500)
                .setStartDelay(350).start();
        tagline.animate().alpha(1f).setDuration(500)
                .setStartDelay(550).start();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SessionManager session = new SessionManager(this);
            Intent intent;
            if (session.isLoggedIn() &&
                    "ACTIVE".equals(session.getStatus())) {

                intent = new Intent(this, MainActivity.class);

            } else {
                session.clearSession();
                intent = new Intent(this, LoginActivity.class);
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_DURATION_MS);
    }
}