package com.example.mwils.april;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.crashlytics.android.Crashlytics;
import com.example.mwils.april.Exercises.Exercises;
import com.example.mwils.april.Nutrition.Nutrition;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;





public class MainActivity extends AppCompatActivity {

    //Creating instance of Firebase authenticator
    private FirebaseAuth auth;

    //creating variables for every button on the page
    Button exercises, nutrition, admin, logout, physio;

    //creating variable to hold the results of the isAdmin check
    private boolean isAdmin = false, isPhysio = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    setUpFields();
    setUpOnClicks();
    toggleFields();
    }//end onCreate method

    /**
     * This method assigns all the buttons to variables
     * to be used for onClick events
     */
    private void setUpFields(){
        //assigning the logout variable to btnLogOut
        logout = findViewById(R.id.btnLogOut);
        //assigning the exercises variable to btnExercises
        exercises = findViewById(R.id.btnExercises);
        //assigning the nutrition variable to btnNutrition
        nutrition = findViewById(R.id.btnNutrition);
        //assigning the admin variable to btnAdmin
        admin = findViewById(R.id.btnAdmin);
        admin.setVisibility(View.INVISIBLE);
        //assigning the physio variable to btnPhysio
        physio = findViewById(R.id.btnPhysio);
        physio.setVisibility(View.INVISIBLE);
    }//end setUpFields

    /**
     * This method sets up on the onClick events for the buttons
     * on the page, both navigation and logout
     */
    private void setUpOnClicks(){
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

    }//end setUpOnClicks

    /**
     * This page connects to the database, takes the currently logged in User's ID
     * and checks it against a record in the DB to see whether or not that user
     * is considered an Admin. If they are, then it will make the Admin button visible, which
     * is invisible by default. This button lets the user access the admin only page
     * of the application. It carries out the same thing for the Physiotherapist page
     */
    private void toggleFields(){
        //creates an instance of the Firebase authentication class
        auth = FirebaseAuth.getInstance();
        //Retrieves the current User's ID to check if they have admin rights
        String uid = auth.getUid();
        //Attempting to check if user is admin
        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Users
        CollectionReference colref = database.collection("Users");
        //Checking for a specific admin user in that Collection
        DocumentReference docRef = colref.document(uid);

        //calls the record down from the Database
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //if it successfully pulls down the document
                        //it sets the boolean to the same value as the database
                        if (documentSnapshot.exists()){
                            isAdmin = documentSnapshot.getBoolean("isAdmin");
                            isPhysio = documentSnapshot.getBoolean("isPhysio");
                            if(isAdmin) {
                                admin.setVisibility(View.VISIBLE);
                                //setting onClick in here so that users don't trigger
                                //an 'invisible button'. this way they can still accidentally press
                                //it, but they won't even know
                                admin.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(MainActivity.this, Admin.class));
                                    }//end onClick method
                                });//end onClickListener
                            }//end if isAdmin visibility checker
                            if(isPhysio) {
                                //if the user is listed as a physiotherapist, then it makes the physio button visible
                                physio.setVisibility(View.VISIBLE);

                                //similar to the admin button, the onclick for physio is being set here
                                //so an invisible button cannot be triggered.
                                physio.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity((new Intent(MainActivity.this, Physio.class)));
                                    }//end onClick method
                                });//end onClickListener
                            }//end if isPhysio visibility checker
                        }//end if statement
                    }//end onSuccess method
                })//end onSuccessListener
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Failed to get doc: "+e);
                    }//end exception
                });//end onFailureListener
    }//end toggleAdmin
}//end class
