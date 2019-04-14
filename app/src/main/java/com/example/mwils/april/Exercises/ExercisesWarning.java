package com.example.mwils.april.Exercises;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mwils.april.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ExercisesWarning extends Activity {
    //Creating String variables to hold the data from the DB after split
    String warnings;
    //Initialising variables to hold the information for the textViews
    TextView warning1, warning2, warning3, warning4, warning5, warning6, warning7, warning8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.exercise_warning);

        setUpFields();
        setData();
    }//end onCreate method

    /**
     * This method assigns the variables to the various elements
     * in the layout file.
     */
    public void setUpFields() {
        //Assigning each of the warning to relevant TextViews
        warning1 = findViewById(R.id.tvWarning1);
        warning2 = findViewById(R.id.tvWarning2);
        warning3 = findViewById(R.id.tvWarning3);
        warning4 = findViewById(R.id.tvWarning4);
        warning5 = findViewById(R.id.tvWarning5);
        warning6 = findViewById(R.id.tvWarning6);
        warning7 = findViewById(R.id.tvWarning7);
        warning8 = findViewById(R.id.tvWarning8);
    }//end setUpFields

    /**
     * This method gathers the data from the Firestore and
     * enters it into the various elements on the tab.
     */
    public void setData() {
        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Exercises
        CollectionReference colref = database.collection("Exercises");
        //Checking for a specific Exercise in that Collection
        DocumentReference docRef = colref.document("Warning");

        //Retrieves the document from the Firestore
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //Assigning the information from the database
                        //to the initialised Strings
                        warnings = documentSnapshot.get("Text").toString();
                        //call the methods that format and place the Strings into the relevant textViews
                        formatWarnings(warnings);
                    }//end on Success
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure( Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to retrieve data " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }//end onFailure
        });//end onFailureListener
    }//end getData

    /**
     * This method takes the Sting retrieved from the DB
     * splits it into a String Array, and then places
     * those values in the relevant textViews for the warnings
     * @param unformat - contains the String from the DB to be formatted
     */
    private void formatWarnings(String unformat){
        //Removes the square bracket from the end of the text
        unformat = unformat.replaceAll("[\\[\\]]","");
        //Splits the String into cells in a String Array
        String [] formatDesc = unformat.split(",");

        //Assigns in the individual Strings into the
        //relevant TextViews
        warning1.setText(formatDesc[0]);
        warning2.setText(formatDesc[1]);
        warning3.setText(formatDesc[2]);
        warning4.setText(formatDesc[3]);
        warning5.setText(formatDesc[4]);
        warning6.setText(formatDesc[5]);
        warning7.setText(formatDesc[6]);
        warning8.setText(formatDesc[7]);
    }//end formatDescription

}//end class