package com.example.mwils.april.Nutrition.NutritionSmoothies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mwils.april.R;

public class NutritionSmoothies_Tab2 extends Fragment {
    private static final String TAG = "Nutrition_Smoothies_Tab2";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrition_smoothies_tab2,container,false);

        return view;
    }
}

