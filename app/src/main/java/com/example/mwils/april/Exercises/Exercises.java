package com.example.mwils.april.Exercises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mwils.april.Exercises.ExerciseProgramme.ExerciseProgramme;
import com.example.mwils.april.Exercises.ExerciseWarmup.ExercisesWarmup;
import com.example.mwils.april.R;

public class Exercises extends AppCompatActivity {

    private Button programme, warmup, warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_main);

       setUpFields();
       setUpOnClicks();


    }//end onCreate method

    /**
    This method assigns the button elements on the page
    to variables to be used for onclick events
     **/
    private void setUpFields(){
        //assigning the programme variable to btnExerProg
        programme = findViewById(R.id.btnExerProg);
        //assigning the warmup variable to btnExerWarmup
        warmup = findViewById(R.id.btnExerWarmup);
        //assigning the warning variable to btnExerWarning
        warning = findViewById(R.id.btnExerWarning);
    }//end setUpFields

    /**
    This method sets all the onClick events for each
    button to enable user navigation
     **/
    private void setUpOnClicks(){
        //navigating the user to the Exercise Programme page
        programme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exercises.this, ExerciseProgramme.class));
            }//end onClick method
        });//end OnClickListener

        //navigating the user to the Warmup/Cooldown page
        warmup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exercises.this, ExercisesWarmup.class));
            }//end onClick method
        });//end OnClickListener

        //displaying the exercise warning page when the warning button is clicked
        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exercises.this, ExercisesWarning.class));
            }//end onClick method
        });//end OnClickListener
    }//end setUpOnClicks
}//end class
