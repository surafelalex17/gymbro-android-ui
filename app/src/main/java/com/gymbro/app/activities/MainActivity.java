package com.gymbro.app.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.gymbro.app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.gymbro.app.fragments.ExercisesFragment;
import com.gymbro.app.fragments.HomeFragment;
import com.gymbro.app.fragments.RoutinesFragment;
import com.gymbro.app.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    public DrawerLayout drawerLayout;

    private Fragment homeFragment;
    private Fragment exercisesFragment;
    private Fragment routinesFragment;
    private Fragment activeFragment;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        drawerLayout   = findViewById(R.id.drawer_layout);

        setupFragments();
        setupBottomNavigation();
        setupDrawer();
    }

    private void setupFragments() {
        homeFragment      = new HomeFragment();
        exercisesFragment = new ExercisesFragment();
        routinesFragment  = new RoutinesFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFragment, "home")
                .add(R.id.fragment_container, exercisesFragment, "exercises").hide(exercisesFragment)
                .add(R.id.fragment_container, routinesFragment, "routines").hide(routinesFragment)
                .commit();

        activeFragment = homeFragment;
    }

    private void setupBottomNavigation() {
        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment targetFragment;

            if (id == R.id.nav_home) {
                targetFragment = homeFragment;
            } else if (id == R.id.nav_exercises) {
                targetFragment = exercisesFragment;
            } else if (id == R.id.nav_routines) {
                targetFragment = routinesFragment;
            } else {
                return false;
            }

            if (targetFragment != activeFragment) {
                getSupportFragmentManager().beginTransaction()
                        .show(targetFragment)
                        .hide(activeFragment)
                        .commit();
                activeFragment = targetFragment;
            }

            return true;
        });

        bottomNav.setSelectedItemId(R.id.nav_home);
    }

    private void setupDrawer() {
        View drawerView = findViewById(R.id.nav_drawer);

        // Fill user info
        TextView tvName    = drawerView.findViewById(R.id.drawer_user_name);
        TextView tvEmail   = drawerView.findViewById(R.id.drawer_user_email);
        TextView tvInitial = drawerView.findViewById(R.id.drawer_avatar_initial);

        String firstName = sessionManager.getFirstName();
        String email     = sessionManager.getUserEmail();

        if (firstName != null) {
            tvName.setText(firstName);
            tvInitial.setText(String.valueOf(firstName.charAt(0)).toUpperCase());
        }
        if (email != null) {
            tvEmail.setText(email);
        }

        // Profile
        drawerView.findViewById(R.id.drawer_profile).setOnClickListener(v -> {
            drawerLayout.closeDrawers();
            // TODO: open ProfileActivity when built
            android.widget.Toast.makeText(this,
                    "Profile coming soon!", android.widget.Toast.LENGTH_SHORT).show();
        });

        // Settings
        drawerView.findViewById(R.id.drawer_settings).setOnClickListener(v -> {
            drawerLayout.closeDrawers();
            startActivity(new Intent(this, SettingsActivity.class));
        });

        // Logout
        drawerView.findViewById(R.id.drawer_logout).setOnClickListener(v -> {
            drawerLayout.closeDrawers();
            showLogoutDialog();
        });
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Log Out", (dialog, which) -> {
                    sessionManager.clearSession();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // Call this from any fragment to open the drawer
    public void openDrawer() {
        drawerLayout.openDrawer(androidx.core.view.GravityCompat.START);
    }

    public void switchTab(int tabId) {
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(tabId);
        }
    }
}