package com.example.mwils.april.Exercises.ExerciseProgramme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mwils.april.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ExerciseProgramme_Tab1 extends Fragment {


    //Creating variables to hold information
    //for all the elements on the activity
    TextView tabtitle, exDescription;
    ImageView diagram;
    //Creating String variables to hold the data from the DB
    String title,  description, url;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_programme_tab1, container, false);

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
        setData();
    }//end onStart

    /*
    This method assigns the variables to the various elements
    in the layout file.
     */
    public void setUpFields() {

        tabtitle = getView().findViewById(R.id.tvEx1Title);
        exDescription = getActivity().findViewById(R.id.tvEx1Description);
        diagram = getActivity().findViewById(R.id.ivExcerise1Diagram);


    }//end setUpFields

    /*
    This method gathers the data from the Firestore and
    enters it into the various elements on the tab.
     */
    public void setData() {

        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Exercises
        CollectionReference colref = database.collection("Exercises");
        //Checking for a specific Exercise in that Collection
        DocumentReference docRef = colref.document("Exercise 1");

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
                        description = description .replaceAll("]","");
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

}//end Class



