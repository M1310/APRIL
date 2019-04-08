package com.example.mwils.april;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Exercises extends AppCompatActivity {

    private Button programme; Button warmup; Button standing;

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

        //navigating the user to the Exercise Programme page
        programme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exercises.this, ExerciseProgramme.class));
            }//end onClick method
        });//end OnClickListener
    }
}
