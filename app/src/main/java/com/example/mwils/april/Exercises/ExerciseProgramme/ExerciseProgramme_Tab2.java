package com.example.mwils.april.Exercises.ExerciseProgramme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mwils.april.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ExerciseProgramme_Tab2 extends Fragment {

    //Creating variables to hold information
    //for all the elements on the activity
    TextView tabtitle, exDescription;
    ImageView diagram;
    //Creating String variables to hold the data from the DB
    String title,  description, url;
    //Creating Button variable to open the exercise tracker
    Button tracker;
    //creating a variable to hold the instance of Firebase Authentication
    FirebaseAuth auth;
    //Creating variables used to check if the user is a client
    boolean isAdmin, isPhysio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_programme_tab2, container, false);

        return view;
    }//end onCreate
    /*
    onStart runs after onCreateView
    allowing me to assign elements to variables without
    getting a Null Pointer exception
     */
    @Override
    public void onStart() {
        super.onStart();
        setUpFields();
        setUpOnClicks();
        setData();
        toggleFields();
    }//end onStart

    /**
    This method assigns the variables to the various elements
    in the layout file.
     **/
    public void setUpFields() {
        tabtitle = getView().findViewById(R.id.tvEx2Title);
        exDescription = getActivity().findViewById(R.id.tvEx2Description);
        diagram = getActivity().findViewById(R.id.ivExcerise2Diagram);
        tracker = getActivity().findViewById(R.id.btnEx2Track);
        //Making the tracker button invisible until the user is identified
        //as a client in the togglefields method
        tracker.setVisibility(View.INVISIBLE);
    }//end setUpFields

    /**
    This method gathers the data from the Firestore and
    enters it into the various elements on the tab.
     **/
    public void setData() {
        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Exercises
        CollectionReference colref = database.collection("Exercises");
        //Checking for a specific Exercise in that Collection
        DocumentReference docRef = colref.document("Exercise 2");

        //Retrieves the document from the Firestore
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //Assigning the information from the database
                        //to the initialised Strings
                        title = documentSnapshot.get("Name").toString();
                        description = documentSnapshot.get("Description").toString();
                        //formatting the String array into individual lines within a single String
                        description = description.replaceAll(",", "\n");
                        description = description .replaceAll("[\\[\\]]","");
                        url = documentSnapshot.get("Diagram").toString();
                    }//end on Success
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed to retrieve data " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }//end onFailure
        }).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                //Once the data has been retrieved
                //it is entered into the elements on the page
                tabtitle.setText(title);
                exDescription.setText(description);
                //Using Glide to load the image from the Firebase storage
                //URL directly into the ImageView
                Glide.with(getView())
                        .load(url)
                        .into(diagram);
            }//end onComplete
        });//end onCompleteListener
    }//end getData

    /**
     * This method shows the 'Tracker' button if the user is neither an Admin or Physiotherpaist
     */
    private void toggleFields(){
        //creates an instance of the Firebase authentication class
        auth = FirebaseAuth.getInstance();
        //Retrieves the current User's ID to check if they have admin rights
        String uid = auth.getCurrentUser().getEmail();
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
                            if(!isAdmin && !isPhysio) {
                                //If the user isn't an admin, and they aren't a physiotherpist
                                //then they are a client, so they'll be able to access the tracker
                                tracker.setVisibility(View.VISIBLE);
                            }//end nested if statement
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

    /**
     * This method sets up the onClick methods for buttons on the page
     */
    private void setUpOnClicks(){
        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigates the user to the tracker page, passing through
                //whatever exercise they were already looking at
                Context context = getContext();
                Intent intent = new Intent(context, ExerciseTracker.class);
                intent.putExtra("Exercise ID",2);
                intent.putExtra("Exercise Name",title);
                startActivity(intent);
            }//end onClick method
        });//end onClickListener
    }//end setUpOnClicks

}//end Class



