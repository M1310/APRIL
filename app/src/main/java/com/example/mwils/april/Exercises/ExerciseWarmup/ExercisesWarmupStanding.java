package com.example.mwils.april.Exercises.ExerciseWarmup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mwils.april.R;

public class ExercisesWarmupStanding extends Fragment {
    private static final String TAG = "Exercise_Warmup_Standing";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercises_standing,container,false);

        return view;
    }
}

