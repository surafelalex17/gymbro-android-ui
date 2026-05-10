package com.gymbro.app.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gymbro.app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.gymbro.app.fragments.HomeFragment;
import com.gymbro.app.fragments.ExercisesFragment;
import com.gymbro.app.fragments.RoutinesFragment;

/**
 * MainActivity
 * File: java/com/gymbro/app/activities/MainActivity.java
 *
 * Host activity for the 3 bottom-tab fragments:
 *   1. Home       → HomeFragment
 *   2. Exercises  → ExercisesFragment
 *   3. Routines   → RoutinesFragment
 *
 * Uses replace-fragment approach for simplicity.
 * Can be upgraded to Navigation Component (NavHostFragment) later.
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    // Keep references to avoid recreating fragments on tab switch
    private Fragment homeFragment;
    private Fragment exercisesFragment;
    private Fragment routinesFragment;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragments();
        setupBottomNavigation();
    }

    /**
     * Initializes all 3 fragments and adds them to the back stack.
     * Only the home fragment is shown initially.
     */
    private void setupFragments() {
        homeFragment = new HomeFragment();
        exercisesFragment = new ExercisesFragment();
        routinesFragment = new RoutinesFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFragment, "home")
                .add(R.id.fragment_container, exercisesFragment, "exercises").hide(exercisesFragment)
                .add(R.id.fragment_container, routinesFragment, "routines").hide(routinesFragment)
                .commit();

        activeFragment = homeFragment;
    }

    /**
     * Sets up the bottom navigation tab switching logic.
     */
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

            // Show selected, hide active
            if (targetFragment != activeFragment) {
                getSupportFragmentManager().beginTransaction()
                        .show(targetFragment)
                        .hide(activeFragment)
                        .commit();
                activeFragment = targetFragment;
            }

            return true;
        });

        // Set default selected tab
        bottomNav.setSelectedItemId(R.id.nav_home);
    }

    /**
     * Programmatically switch to a specific tab from child fragments.
     * @param tabId R.id.nav_home / nav_exercises / nav_routines
     */
    public void switchTab(int tabId) {
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(tabId);
        }
    }
}