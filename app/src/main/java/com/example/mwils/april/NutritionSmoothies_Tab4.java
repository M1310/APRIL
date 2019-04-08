package com.example.mwils.april;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NutritionSmoothies_Tab4 extends Fragment {
    private static final String TAG = "Nutrition_Smoothies_Tab4";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrition_smoothies_tab4,container,false);

        return view;
    }
}

