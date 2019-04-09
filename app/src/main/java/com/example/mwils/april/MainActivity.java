package com.example.mwils.april;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mwils.april.Exercises.Exercises;
import com.example.mwils.april.Nutrition.Nutrition;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.StructuredQuery;

import java.sql.SQLOutput;


public class MainActivity extends AppCompatActivity {

    //Creating instance of Firebase authenticator
    private FirebaseAuth auth;

    //creating variables for every button on the page
    private Button logout; Button exercises; Button nutrition; Button admin;

    //creating variable to hold the results of the isAdmin check
    private boolean isAdmin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //creates an instance of the Firebase authentication class
        auth = FirebaseAuth.getInstance();
        //Retrieves the current User's ID to check if they have admin rights
        String uid = auth.getUid();
        //Attempting to check if user is admin
        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Users
        CollectionReference colref = database.collection("Users");
        //Checking for a specific admin user in that Collection, this is hardcoded until I figure
        //out how to properly call .getuid() without crashing the app
        DocumentReference docRef = colref.document(uid);

        //assigning the logout variable to btnLogOut
        logout = findViewById(R.id.btnLogOut);
        //assigning the exercises variable to btnExercises
        exercises = findViewById(R.id.btnExercises);
        //assigning the nutrition variable to btnNutrition
        nutrition = findViewById(R.id.btnNutrition);
        //assigning the admin variable to btnAdmin
        admin = findViewById(R.id.btnAdmin);

        //calls the record down from the Database
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //if it successfully pulls down the document
                        //it sets the boolean to the same value as the database
                        if (documentSnapshot.exists()){
                            System.out.println("got here 1");
                            isAdmin = documentSnapshot.getBoolean("isAdmin");

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
                            }else{
                                admin.setVisibility(View.INVISIBLE);
                            }//end else statement
                        }//end if statement
                    }//end onSuccess method
                })//end onSuccessListener
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Failed to get doc: "+e);
                    }//end exception
                });//end onFailureListener


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

        //hiding the admin settings button
        //will display if the user is a
        //verified admin user




    }//end onCreate method
}//end class
