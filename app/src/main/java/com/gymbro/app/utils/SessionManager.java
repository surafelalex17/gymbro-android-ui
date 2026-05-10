package com.gymbro.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SessionManager
 * File: java/com/gymbro/app/utils/SessionManager.java
 *
 * Manages user login state using SharedPreferences.
 *
 * BACKEND INTEGRATION NOTE:
 * Replace SharedPreferences storage with:
 * - JWT token storage in EncryptedSharedPreferences
 * - Token refresh logic via Retrofit interceptor
 * - User ID fetched from your Node.js /auth/me endpoint
 */
public class SessionManager {

    private static final String PREF_NAME = "gymbro_session";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_AUTH_TOKEN = "auth_token";   // reserved for backend

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    /**
     * Call this after successful login.
     * @param name  Display name from backend response
     * @param email User email
     * @param token JWT token from Node.js (pass empty string for now)
     */
    public void createLoginSession(String name, String email, String token) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_AUTH_TOKEN, token);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUserName() {
        return prefs.getString(KEY_USER_NAME, "User");
    }

    public String getUserEmail() {
        return prefs.getString(KEY_USER_EMAIL, "");
    }

    public String getAuthToken() {
        return prefs.getString(KEY_AUTH_TOKEN, "");
    }

    /**
     * Returns the first letter of the display name for avatar circles.
     */
    public String getAvatarInitial() {
        String name = getUserName();
        return name.isEmpty() ? "?" : String.valueOf(name.charAt(0)).toUpperCase();
    }

    /**
     * Clears all session data (logout).
     */
    public void logout() {
        editor.clear();
        editor.apply();
    }
}