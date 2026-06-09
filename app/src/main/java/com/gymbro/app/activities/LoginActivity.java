package com.gymbro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymbro.app.R;
import com.gymbro.app.data.remote.RetrofitClient;
import com.gymbro.app.data.remote.dto.ApiResponse;
import com.gymbro.app.data.remote.dto.AuthRequest;
import com.gymbro.app.data.remote.dto.AuthResponse;
import com.gymbro.app.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sessionManager = new SessionManager(this);

        if (sessionManager.isLoggedIn()) {
            goToMain();
            return;
        }

        etEmail     = findViewById(R.id.et_email);
        etPassword  = findViewById(R.id.et_password);
        btnLogin    = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(v -> loginUser());
        btnRegister.setOnClickListener(v -> goToRegister());
    }

    private void loginUser() {
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        btnLogin.setEnabled(false);
        btnLogin.setText("Signing in...");

        AuthRequest request = new AuthRequest(email, password);

        RetrofitClient.getInstance(this)
                .getApiService()
                .login(request)
                .enqueue(new Callback<ApiResponse<AuthResponse>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<AuthResponse>> call,
                                           Response<ApiResponse<AuthResponse>> response) {
                        btnLogin.setEnabled(true);
                        btnLogin.setText("Sign In");

                        if (response.isSuccessful() && response.body() != null) {
                            AuthResponse data = response.body().getData();

                            sessionManager.saveSession(
                                    data.getToken(),
                                    data.getUser().getId(),
                                    data.getUser().getEmail(),
                                    data.getUser().getFirstName(),
                                    data.getUser().getStatus());
                            goToMain();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Invalid email or password",
                                    Toast.LENGTH_SHORT).show();

                            try {
                                String errorBody = response.errorBody().string();
                                org.json.JSONObject json = new org.json.JSONObject(errorBody);
                                String message = json.getString("message");
                                Toast.makeText(LoginActivity.this, message,
                                        Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this,
                                        "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<AuthResponse>> call,
                                          Throwable t) {
                        btnLogin.setEnabled(true);
                        btnLogin.setText("Sign In");
                        Toast.makeText(LoginActivity.this,
                                "Cannot connect to server. Is it running?",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void goToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}