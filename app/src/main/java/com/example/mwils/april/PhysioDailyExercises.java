package com.example.mwils.april;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PhysioDailyExercises extends AppCompatActivity {

    //Initialising variables to hold elements on the page
    private TextView idResult, nameResult, repResult, diffResult;
    private Spinner dropdown;
    private Button search;

    //Initialising variable to hold firestore instance
    FirebaseFirestore db;

    //Calling the SharedPreferences variables
    //to hold global information about the user
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private String phEmail;

    //Initialising a String value used for populating the dropdown
    private String [] clients;

    //Initialising Strings to be loaded into the TextViews from the db
    String dbID, dbName, dbRep, dbDiff, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physio_daily_exercises);

        //Calling an instance of Firestore
        db = FirebaseFirestore.getInstance();

        //Gets the current date to the perform the search
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        date = dateFormat.format(new Date()); // Find today's date

        setUpFields();
        populateSpinner();
        setUpOnClicks();
    }//end onCreate

    /**
     * This method assigns the elements on the page to the variables
     */
    private void setUpFields(){
        idResult = findViewById(R.id.exIDResult);
        nameResult = findViewById(R.id.exNameResult);
        repResult = findViewById(R.id.exRepResult);
        diffResult = findViewById(R.id.exDiffResult);
        search = findViewById(R.id.btnSearch);
        dropdown = findViewById(R.id.clientSpinner);
    }//end setUpFields

    /**
     * This method retrieves the data for the specified client
     * from the database and displays it
     */
    private void getData(){
        db.collection("ExerciseTracker").document(dropdown.getSelectedItem().toString()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.get("Date")!= null) {
                            if (date.equalsIgnoreCase(documentSnapshot.get("Date").toString())) {
                                dbID = documentSnapshot.get("Exercise ID").toString();
                                dbName = documentSnapshot.get("Exercise Name").toString();
                                dbRep = documentSnapshot.get("Repetitions").toString();
                                dbDiff = documentSnapshot.get("Difficulty").toString();

                                //Once the data has been retrieved, it places the values in the textViews
                                idResult.setText(dbID);
                                nameResult.setText(dbName);
                                repResult.setText(dbRep);
                                diffResult.setText(dbDiff);
                            } else {
                                Toast.makeText(getApplicationContext(), "This person hasn't tracked any exercises today!", Toast.LENGTH_SHORT).show();
                            }//end nested ifelse statement
                        }//end if statement
                    }//end onSuccess
                });//end onSuccessListener
    }//end getData

    /**
     * This method populates the dropdown box
     * with all the clients that the currently logged in
     * physiotherapist has
     */
    private void populateSpinner(){
        //Calling the sharedpreferences to access the global variable
        shared = getApplicationContext().getSharedPreferences("YourSessionName",MODE_PRIVATE);
        editor = shared.edit();

        //sets the physiotherapists to the current users email address
        phEmail = (shared.getString("currentUser",null));

        //retrieves all clients tied to the phyisotherapist
        //then splits the dataObject into the array
        db.collection(phEmail).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<String> list = new ArrayList<>();
                            //takes the return snapshot, and gets the id (document name)
                            //for each and adds them to a list
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.getId());
                            }//end list

                            //takes the list item and adds it to an array adapter to placed in the spinner
                            ArrayAdapter<String> spin = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                            //populates the drop down
                            dropdown.setAdapter(spin);
                        }//end if statement
                    }//end onComplete
                });//end onCompleteListener
    }//end populateSpinner

    /**
     * Sets up the OnClick event for the button on the page
     */
    private void setUpOnClicks(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }//end onClick
        });//end onClickListener
    }//end setUpOnClicks
}//end class
