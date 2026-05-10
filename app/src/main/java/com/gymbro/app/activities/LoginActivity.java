package com.gymbro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gymbro.app.R;
import com.gymbro.app.utils.SessionManager;

/**
 * LoginActivity
 * File: java/com/gymbro/app/activities/LoginActivity.java
 *
 * Handles user login UI. Currently uses mock validation.
 *
 * BACKEND INTEGRATION:
 * Replace the mock login block with a Retrofit POST call to:
 *   POST /api/auth/login { email, password }
 *   Response: { token, user: { id, name, email } }
 * Then call sessionManager.createLoginSession(name, email, token)
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sessionManager = new SessionManager(this);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        // Login button click
        btnLogin.setOnClickListener(v -> attemptLogin());

        // Register button click
        btnRegister.setOnClickListener(v -> {
            // TODO: Navigate to RegisterActivity when built
            Toast.makeText(this, "Registration coming soon!", Toast.LENGTH_SHORT).show();
        });

        // Forgot password
        findViewById(R.id.tv_forgot).setOnClickListener(v -> {
            Toast.makeText(this, "Password reset coming soon!", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Validates input and performs login.
     * Currently uses mock login — replace with Retrofit API call.
     */
    private void attemptLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // --- Input validation ---
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return;
        }

        // --- MOCK LOGIN (replace with Retrofit call) ---
        // Any valid email/password combo works for now
        String displayName = extractNameFromEmail(email);

        // Simulate loading state
        btnLogin.setText("Signing in…");
        btnLogin.setEnabled(false);

        // Simulate network delay
        btnLogin.postDelayed(() -> {
            // Save session
            sessionManager.createLoginSession(displayName, email, "mock_token_123");

            // Navigate to main app
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 800); // Simulated 800ms delay
    }

    /**
     * Extracts a display name from the email address.
     * e.g. "john.doe@gmail.com" → "John"
     */
    private String extractNameFromEmail(String email) {
        String local = email.split("@")[0];        // john.doe
        String[] parts = local.split("[._]");       // [john, doe]
        String first = parts[0];                    // john
        return first.substring(0, 1).toUpperCase()
                + (first.length() > 1 ? first.substring(1).toLowerCase() : "");
    }
}