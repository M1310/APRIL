package com.example.mwils.april;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class CreateClient extends AppCompatActivity {

    //initialising the variables for the items in the activity
    private Button btnCreateClient;
    private EditText email, password, confirm, name;
    private String phEmail, emailS, passwordS, confirmS, nameS;

    //initialising a variable for firebase auth
    private FirebaseAuth auth;

    //initialising a variable for firestore database
    private FirebaseFirestore db;

    //Calling the SharedPreferences variables
    //to hold global information about the user
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    //initialising a variable for Firestore Collection reference
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        //Calling the sharedpreferences to access the global variable
        shared = getApplicationContext().getSharedPreferences("YourSessionName",MODE_PRIVATE);
        editor = shared.edit();


        //sets the physiotherapists to the current users email address
        phEmail = (shared.getString("currentUser",null));

        setUpFields();
        setUpOnClick();
    }//end onCreate

    /**
     * This nethod assigns the EditText elements on the page to variables
     * to be used in the creation of a new user account. It also assigns a variable
     * to the actual create user button to be used in the onClick event.
     */
    private void setUpFields(){
        //Assigning the variables to the text fields in the activity
        email = findViewById(R.id.etNewClientEmail);
        password = findViewById(R.id.etClientPassword);
        confirm = findViewById(R.id.etClientPasswordConfirm);
        name = findViewById(R.id.etClientName);

        //Assigning btnCreateUser to the button in the activity
        btnCreateClient = findViewById(R.id.btnConfirmClientCreate);
    }//end setUpFields

    /**
     * This method sets up the onClick event for the Create User button
     * This button only calls the createUser() method
     */
    private void setUpOnClick(){
        //Setting onClickListener to create the new user
        btnCreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }//end onClick method
        });//end onClickListener method
    }//end setUpOnClick

    /**
     * This method checks that all the fields have been entered
     * It'll also check that the password and the confirmation are the same
     */
    private Boolean validate(){
        boolean result = false;

        //Retrieves the string values of the text entered
        //into each field in the activity
        emailS = email.getText().toString().trim();
        passwordS = password.getText().toString();
        confirmS = confirm.getText().toString();
        nameS = name.getText().toString();

        //Checks if any of the fields are empty
        if(emailS.isEmpty() || passwordS.isEmpty() || confirmS.isEmpty() || nameS.isEmpty()){
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
        }//end if statement
        //if the fields aren't empty, check the password and the confirmation match
        else if(passwordS.equals(confirmS)){
            result = true;
        }//end nested if statement
        else{
            Toast.makeText(this, "Passwords do not match",Toast.LENGTH_SHORT).show();
        }//end nestes else statement

        return result;
    }//end validate method

    /**
     * This method opens a connnection to the authentication database and creates a new user
     * Once the user has been added, it then takes the User ID and adds that to
     * the User database, while also marking them as an Admin user if the checkbox
     * has been ticked
     */
    private void createUser(){
        //creating an instance of Firebase Database
        db = FirebaseFirestore.getInstance();

        //creating an instance of Firebase Auth
        auth = FirebaseAuth.getInstance();

        //checks that the fields have been populated
        if(validate()){
            //extracting the Strings from the EditText fields
            String newEmail = email.getText().toString().trim();
            String newPass = password.getText().toString().trim();

            //Calls the instance of Firebase auth and creates the user
            auth.createUserWithEmailAndPassword(newEmail,newPass)
                    .addOnCompleteListener(CreateClient.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //lets the user know if the creation failed
                            if(!task.isSuccessful()){
                                Toast.makeText(CreateClient.this, "User Creation failed: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }//end if statement
                            else {
                                //if the creation was successful, let the user know, and add them to the users database
                                //also resets the fields in case the user wanted to make another user
                                email.setText("");
                                password.setText("");
                                confirm.setText("");
                                name.setText("");
                                Toast.makeText(CreateClient.this,"User Creation Successful!", Toast.LENGTH_SHORT).show();

                                //Creating a map object to hold the admin toggle for the client
                                Map<String, Object> client = new HashMap<>();
                                client.put("Client Name", nameS);
                                client.put("Email Address", emailS);
                                //Placing the client information in the Physio's collection
                                db.collection(phEmail).document(emailS).set(client);
                            }//end else statement
                        }//end onComplete method
                    });//end onCompleteListener
        }//end if statement
    }//end createUser
}//end Class
