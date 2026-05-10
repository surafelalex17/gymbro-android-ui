package com.gymbro.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.gymbro.app.R;


public class ExercisesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // This links to fragment_exercises.xml
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }
}