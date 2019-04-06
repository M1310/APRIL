package com.example.mwils.april;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Nutrition extends AppCompatActivity {

    private TextView mTextMessage;
    //Creating variables to hold tie to the buttons
    private Button info; Button illness; Button meals; Button smoothies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_main);

        //assigning the info variable to btnNutrInfo
        info = (Button) findViewById(R.id.btnNutrInfo);
        //assigning the illness variable to btnNutrIllness
        illness = (Button) findViewById(R.id.btnNutrIllness);
        //assigning the meals variable to btnNutrMeals
        meals = (Button) findViewById(R.id.btnNutrMeals);
        //assigning the smoothies variable to btnNutrSmoothies
        smoothies = (Button) findViewById(R.id.btnNutrSmoothies);

        //navigating the user to the Nutrition Main page
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Nutrition.this, NutritionInformation.class));
            }//end onClick method
        });//end OnClickListener
    }//end onCreate method

}
