package com.example.mwils.april.Nutrition.NutritionMeals;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mwils.april.R;

public class NutritionMeals_Tab1 extends Fragment {
    private static final String TAG = "Nutrition_Meals_Tab1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrition_meals_tab1,container,false);

        return view;
    }
}

