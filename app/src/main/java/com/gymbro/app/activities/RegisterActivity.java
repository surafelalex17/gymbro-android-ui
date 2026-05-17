package com.gymbro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.gymbro.app.R;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.data.remote.dto.AuthResponse;
import com.gymbro.app.data.remote.dto.RegisterRequest;
import com.gymbro.app.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvError, tvLogin, btnBack;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sessionManager = new SessionManager(this);

        // Wire up views
        etFirstName       = findViewById(R.id.et_firstname);
        etLastName        = findViewById(R.id.et_lastname);
        etEmail           = findViewById(R.id.et_email);
        etPassword        = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister       = findViewById(R.id.btn_register);
        tvError           = findViewById(R.id.tv_error);
        tvLogin           = findViewById(R.id.tv_login);
        btnBack           = findViewById(R.id.btn_back);

        btnRegister.setOnClickListener(v -> registerUser());

        tvLogin.setOnClickListener(v -> {
            finish(); // goes back to LoginActivity
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void registerUser() {
        String firstName       = etFirstName.getText().toString().trim();
        String lastName        = etLastName.getText().toString().trim();
        String email           = etEmail.getText().toString().trim();
        String password        = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // ── Validation ────────────────────────────────────────────────────────
        if (firstName.isEmpty() || lastName.isEmpty() ||
                email.isEmpty() || password.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }

        if (password.length() < 8) {
            showError("Password must be at least 8 characters.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Please enter a valid email address.");
            return;
        }

        // ── API Call ──────────────────────────────────────────────────────────
        hideError();
        btnRegister.setEnabled(false);
        btnRegister.setText("Creating account...");

        RegisterRequest request = new RegisterRequest(
                email, password, firstName, lastName
        );

        RetrofitClient.getInstance(this)
                .getApiService()
                .register(request)
                .enqueue(new Callback<ApiResponse<AuthResponse>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<AuthResponse>> call,
                                           Response<ApiResponse<AuthResponse>> response) {
                        btnRegister.setEnabled(true);
                        btnRegister.setText("Create Account");

                        if (response.isSuccessful() && response.body() != null) {
                            AuthResponse data = response.body().getData();

                            // Save session
                            sessionManager.saveSession(
                                    data.getToken(),
                                    data.getUser().getId(),
                                    data.getUser().getEmail(),
                                    data.getUser().getFirstName()
                            );

                            Toast.makeText(RegisterActivity.this,
                                    "Welcome, " + data.getUser().getFirstName() + "!",
                                    Toast.LENGTH_SHORT).show();

                            goToMain();

                        } else {
                            // Show server error message
                            showError("This email is already registered.");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<AuthResponse>> call,
                                          Throwable t) {
                        btnRegister.setEnabled(true);
                        btnRegister.setText("Create Account");
                        showError("Cannot connect to server. Try again.");
                    }
                });
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showError(String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        tvError.setVisibility(View.GONE);
    }
}