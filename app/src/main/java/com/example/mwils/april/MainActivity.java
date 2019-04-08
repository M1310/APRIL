package com.example.mwils.april;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    //Creating instance of Firebase authenticator
    private FirebaseAuth auth;

    //assigns a variable to the logout button
    //Creating variable for the LogOut button
    private Button logout; Button exercises; Button nutrition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StorageReference logoRef;
        logoRef = FirebaseStorage.getInstance().getReference();

        //creates an instance of the main class
        auth = FirebaseAuth.getInstance();

        //assignsing the logout variable to btnLogOut
        logout = (Button)findViewById(R.id.btnLogOut);
        //assigning the exercises variable to btnExercises
        exercises = (Button)findViewById(R.id.btnExercises);
        //asigning the nutrition variable to btnNutrition
        nutrition = (Button)findViewById(R.id.btnNutrition);

        //logging the user out when the button is clicked
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logs the user out of the application
                auth.signOut();
                finish();
                //redirects the user back to the login page
                startActivity(new Intent(MainActivity.this, LoginPage.class));
            }//end onClick method
        });//end onClickListener

        //navigating the user to the Nutrition Main page
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Nutrition.class));
            }//end onClick method
        });//end onClickListener

        //navigating the user to the Exercises Main page
        exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Exercises.class));
            }//end onClick method
        });//end onClickListener
    }//end onCreate method
}//end class
