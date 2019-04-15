package com.example.mwils.april;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Physio extends AppCompatActivity {

    //creating a variable to hold the btnCreateUser
    private Button btnCreateClient, btnViewExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physio);

        setUpFields();
        setUpOnClicks();
    }//end onCreate method

    /**
     * This method assigns the button elements on the page
     * to variables to be used for onclick events
     */
    private void setUpFields(){
        //assigning btnCreateUser to the Create User button on the admin page
        btnCreateClient = findViewById(R.id.btnCreateClient);

        //assiging btnViewExercises to the Physio Daily Tracker page
        btnViewExercises = findViewById(R.id.btnClientTracker);
    }//end setUpFields

    /**
     * This method sets the onClick event to navigate the user
     * through the application
     */
    private void setUpOnClicks(){
        //setting an onclicklistener to navigate the user to the correct page
        btnCreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Physio.this, CreateClient.class));
            }//end onClick method
        });//end onClickListener

        //setting an onclicklistener to take the user to the daily activity tracker page
        btnViewExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Physio.this, PhysioDailyExercises.class));
            }//end onClick
        });//end onClickListener
    }//end setUpOnClicks
}//end class
