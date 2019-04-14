package com.example.mwils.april.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mwils.april.Nutrition.NutritionInformation.NutritionInformation;
import com.example.mwils.april.Nutrition.NutritionMeals.NutritionMeals;
import com.example.mwils.april.Nutrition.NutritionSmoothies.NutritionSmoothies;
import com.example.mwils.april.R;

public class Nutrition extends AppCompatActivity {

    private TextView mTextMessage;
    //Creating variables to hold tie to the buttons
    private Button info; Button illness; Button meals; Button smoothies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_main);

    setUpFields();
    setUpOnClicks();
    }//end onCreate method

    /**
     * This method assigns the button elements on the page
     * to variables to be used for onclick events
     */
    private void setUpFields(){
        //assigning the info variable to btnNutrInfo
        info = findViewById(R.id.btnExerProg);
        //assigning the illness variable to btnNutrIllness
        illness = findViewById(R.id.btnExerWarmup);
        //assigning the meals variable to btnNutrMeals
        meals = findViewById(R.id.btnExerStanding);
        //assigning the smoothies variable to btnNutrSmoothies
        smoothies =  findViewById(R.id.btnNutrSmoothies);
    }//end setUpFields

    /**
     * This method creates onClick events to each of the buttons
     * to navigate the user around the application
     */
    private void setUpOnClicks(){
        //navigating the user to the Nutrition Information page
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Nutrition.this, NutritionInformation.class));
            }//end onClick method
        });//end OnClickListener
        //navigating the user to the Meal Ideas page
        meals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Nutrition.this, NutritionMeals.class));
            }//end onClick method
        });//end OnClickListener
        //navigating the user to the Smoothie Recipes page
        smoothies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Nutrition.this, NutritionSmoothies.class));
            }//end onClick method
        });//end OnClickListener
        //navigating the user to the Feeling Sick? page
        illness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Nutrition.this, NutritionSick.class));
            }//end onClick method
        });//end OnClickListener
    }//end setUpOnClicks
}//end class
