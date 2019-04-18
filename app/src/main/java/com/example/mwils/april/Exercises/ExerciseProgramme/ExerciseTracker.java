package com.example.mwils.april.Exercises.ExerciseProgramme;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mwils.april.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ExerciseTracker extends AppCompatActivity {
    private LocalDateTime curentDate;
    //initialising a variable for firestore database
    private FirebaseFirestore db;

    //Initialising variables to hold the information being passed
    //over in the intent
    private int intentID;
    private  String intentTitle, exID;

    //Calling the SharedPreferences variables
    //to hold global information about the user
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    String clEmail;

//Initialising the variables to assigns the elements on the page to
TextView tvExName, tvExID;
EditText etReps, etDiff;
Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_tracker);

        retrieveIntent();
        setClientEmail();
        setUpFields();
        setUpOnClicks();

    }//end onCreate

    /**
     * This method assigns all the fields on the page to variables
     * and also sets the values of the exercise name and ID to whatever
     * was passed over the in the intent
     */
    private void setUpFields(){
        //Assigning the elements on the page to variables
        tvExName = findViewById(R.id.exName);
        tvExID = findViewById(R.id.exIDLabel);
        etReps = findViewById(R.id.etReps);
        etDiff = findViewById(R.id.etDiff);

        //Setting the text of the two labels to the values passed through
        //from the intent
        tvExName.setText(intentTitle);
        tvExID.setText(exID);

        //Assigning the enter varible to the enter button
        enter = findViewById(R.id.btnEnterTracking);
    }//end setUpFields

    /**
     * This method takes the information from the EditText areas, enters them into a HashMap
     * and then enters it into the Database
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void enterData(){
        //Creating an instance of the Firebase Firestore
        db = FirebaseFirestore.getInstance();

        //Creating a HashMap to hold the client's exercise data
        Map<String, Object> client = new HashMap<>();


        //The exercise data will be tracked via the date, so this variable will hold the
        //current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String date = dateFormat.format(new Date()); // Find today's date

        int reps = Integer.parseInt(etReps.getText().toString());

        //Checking if any fields are empty
        if(etReps.getText().toString().isEmpty() || etDiff.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else if(reps < 1 || reps >10){
            Toast.makeText(getApplicationContext(), "Pleae a difficulty between 1 and 10", Toast.LENGTH_SHORT).show();
        }else{
            client.put("Date", date);
            client.put("Exercise ID", exID);
            client.put("Exercise Name", intentTitle);
            client.put("Repetitions", etReps.getText().toString());
            client.put("Difficulty",etDiff.getText().toString());

            db.collection("ExerciseTracker").document(clEmail).set(client)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ExerciseTracker.this, "Tracking Successful", Toast.LENGTH_SHORT).show();
                        }//end onSuccess
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ExerciseTracker.this, "Enter Tracking Failed: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }//end onFailure
            });//end onFailureListener
        }//end else statement

    }//end enterData

    /**
     * This method retrieves the values passed over in the intent to identify
     * which exercise was performed and the ID of that exercise
     */
    private void retrieveIntent(){
        //Retrieving the data being passed through from the previous page
        intentTitle = getIntent().getStringExtra("Exercise Name");
        intentID = getIntent().getIntExtra("Exercise ID", 0);
        exID = Integer.toString(intentID);
    }//end method retrieve intent

    /**
     * This method access the sharedpreferences for the application
     * and retrieves the current logged in user's email address
     * to be later used as a document reference
     */
    private void setClientEmail(){
        //Calling the sharedpreferences to access the global variable
        shared = getApplicationContext().getSharedPreferences("YourSessionName",MODE_PRIVATE);
        editor = shared.edit();

        //Initialising a string to hold the current users email address
        //that will be used to post the data into the database
        clEmail = (shared.getString("currentUser",null));
    }//end method setClientEmail

    /**
     * This method assigns an onClickListener to the enter button
     * lets the user know if it worked out not
     */
    private void setUpOnClicks(){
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterData();
            }//end onClick
        });//end onClickListener
    }//end setUpOnClicks
}//end class
