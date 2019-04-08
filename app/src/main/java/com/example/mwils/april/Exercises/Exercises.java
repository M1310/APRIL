package com.example.mwils.april.Exercises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mwils.april.Exercises.ExerciseProgramme.ExerciseProgramme;
import com.example.mwils.april.R;

public class Exercises extends AppCompatActivity {

    private Button programme; Button warmup; Button standing; Button warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_main);

        //assigning the programme variable to btnExerProg
        programme = (Button) findViewById(R.id.btnExerProg);
        //assigning the warmup variable to btnExerWarmup
        warmup = (Button) findViewById(R.id.btnExerWarmup);
        //assigning the standing variable to btnExerStanding
        standing = (Button) findViewById(R.id.btnExerStanding);
        //assing the warning varible to btnExerWarning
        warning = (Button) findViewById(R.id.btnExerWarning);

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

        //navigating the user to the Standing Exercises page
        standing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exercises.this, ExercisesStanding.class));
            }//end onClick method
        });//end OnClickListener

        //displaying the exercise warning pop-up box when the warning button is clicked
        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exercises.this, ExercisesWarning.class));
            }//end onClick method
        });//end OnClickListener

    }//end onCreate method
}//end class
