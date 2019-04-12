package com.example.mwils.april.Nutrition;

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

public class NutritionSick extends Activity {
    //Creating String variables to hold the data from the DB after split
    String information;
    //Initialising variables to hold the information for the textViews
    TextView tv1, tv2, tv3 ,tv4, tv5, tv6, tv7, tv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_nutrition_sick);

        setUpFields();
        setData();
    }//end onCreate method

    /**
     * This method assigns the variables to the various elements
     * in the layout file.
     */
    public void setUpFields() {
        //Assigning each of the lines of information to relevant TextViews
        tv1 = findViewById(R.id.tv1Sickness);
        tv2 = findViewById(R.id.tv2Sickness);
        tv3 = findViewById(R.id.tv3Sickness);
        tv4 = findViewById(R.id.tv4Sickness);
        tv5 = findViewById(R.id.tv5Sickness);
        tv6 = findViewById(R.id.tv6Sickness);
        tv7 = findViewById(R.id.tv7Sickness);
        tv8 = findViewById(R.id.tv8Sickness);
    }//end setUpFields

    /**
     * This method gathers the data from the Firestore and
     * enters it into the various elements on the tab.
     */
    public void setData() {
        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Exercises
        CollectionReference colref = database.collection("Sickness");
        //Checking for a specific Exercise in that Collection
        DocumentReference docRef = colref.document("Information");

        //Retrieves the document from the Firestore
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //Assigning the information from the database
                        //to the initialised Strings
                        information = documentSnapshot.get("Text").toString();
                        //call the methods that format and place the Strings into the relevant textViews
                        formatInformation(information);
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
    private void formatInformation(String unformat){
        //Removes the square bracket from the end of the text
        unformat = unformat.replaceAll("]","");
        //Splits the String into cells in a String Array
        String [] formatDesc = unformat.split(",");

        //Assigns in the individual Strings into the
        //relevant TextViews
        tv1.setText(formatDesc[0]);
        tv2.setText(formatDesc[1]);
        tv3.setText(formatDesc[2]);
        tv4.setText(formatDesc[3]);
        tv5.setText(formatDesc[4]);
        tv6.setText(formatDesc[5]);
        tv7.setText(formatDesc[6]);
        tv8.setText(formatDesc[7]);
    }//end formatDescription

}//end class