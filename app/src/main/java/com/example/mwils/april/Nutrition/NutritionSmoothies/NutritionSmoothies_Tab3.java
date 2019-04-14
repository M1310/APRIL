package com.example.mwils.april.Nutrition.NutritionSmoothies;

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


public class NutritionSmoothies_Tab3 extends Fragment {
    //Creating variables to hold information
    //for all the elements on the activity
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    ImageView diagram;
    //Creating String variables to hold the data from the DB
    String description, url;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrition_smoothies_tab3, container, false);

        return view;
    }//end onCreate

    /**
     * onStart runs after onCreateView in fragments
     * allowing me to assign elements to variables without
     * getting a Null Pointer exception
     */
    @Override
    public void onStart() {
        super.onStart();
        setUpFields();

        setData();
    }//end onStart

    /**
     * This method assigns the variables to the various elements
     * in the layout file.
     */
    public void setUpFields() {
        tv1 = getView().findViewById(R.id.tv1Smoothies3);
        tv2 = getView().findViewById(R.id.tv2Smoothies3);
        tv3 = getView().findViewById(R.id.tv3Smoothies3);
        tv4 = getView().findViewById(R.id.tv4Smoothies3);
        tv5 = getView().findViewById(R.id.tv5Smoothies3);
        tv6 = getView().findViewById(R.id.tv6Smoothies3);
        diagram = getView().findViewById(R.id.ivSmoothies3Diagram);
    }//end setUpFields

    /**
     * This method gathers the data from the Firestore and
     * enters it into the various elements on the tab.
     */
    public void setData() {

        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Exercises
        CollectionReference colref = database.collection("Smoothies");
        //Checking for a specific Exercise in that Collection
        DocumentReference docRef = colref.document("Recipe 3");

        //Retrieves the document from the Firestore
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //Assigning the information from the database
                        //to the initialised Strings
                        description = documentSnapshot.get("Text").toString();
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
                //Calling methods to format and display
                //the info that's been called down
                formatText(description);
                formatDiagram(url);
                //formatDiagram(url);
            }//end onComplete
        });//end onCompleteListener
    }//end getData

    /**
     * This method takes the Sting retrieved from the DB
     * splits it into a String Array, and then places
     * those values in the relevant textViews for titles
     * @param unformat - contains the String from the DB to be formatted
     */
    private void formatText(String unformat){
        //Removes the square bracket from the end of the text
        unformat = unformat.replaceAll("[\\[\\]]","");
        //Splits the String into cells in a String Array
        String [] formatText = unformat.split(",");

        //Assigns in the individual Strings into the
        //relevant TextViews
        //having to start at 5 because I'm an idiot and put the title in last
        tv1.setText(formatText[5]);
        tv2.setText(formatText[0]);
        tv3.setText(formatText[1]);
        tv4.setText(formatText[2]);
        tv5.setText(formatText[3]);
        tv6.setText(formatText[4]);
    }//end formatTitles

    /**
     * This method uses Glide to load the image from the URL
     * into the ImageView on the page
     * @param url - contains the download URL for the diagram
     */
    private void formatDiagram(String url){
        Glide.with(getView())
                .load(url)
                .into(diagram);
    }//end formatDiagram

}//end Class



