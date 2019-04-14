package com.example.mwils.april;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    //Initialising the Button variable
    private Button btnReset;
    //Initlaising the EditText variable that will hold the user's email address
    private EditText etEmail;
    //Initialising variable to hold instance of Firebase authentication
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setUpFields();
        setUpOnClick();

    }//end onCreate

    /**
     * This method assigns the elements on the page
     * to variables to used for entering text and
     * onClick events.
     */
    private void setUpFields(){
        //assigning btnReset variable to the reset button
        btnReset = findViewById(R.id.btnResetPass);
        //assigning etEmail variable to the email field
        etEmail = findViewById(R.id.etEmail);
    }//end setUpFields

    /**
     * This method sets the onClickListener for the ForgotPassword
     * All this does it call the forgotPassword() method
     */
    private void setUpOnClick(){
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword();
            }//end onClick method
        });//end onClickListener method
    }//end setUpOnClick

    /**
     * This method takes the email address that the user entered and asks Firebase
     * to send them a password reset email. If it fails to find an associated account
     * it'll let the user know. It'll also only send if the user actually enters text
     */
    private void forgotPassword(){
        //creating an instance of Firebase authentication
        auth = FirebaseAuth.getInstance();
        //retrieving the users' email address from the editText
        String email = etEmail.getText().toString().trim();

        //performing validation to make sure the user actually
        //filled the email field
        if(email.isEmpty()){
            Toast.makeText(ForgotPassword.this, "Please enter an email address.",Toast.LENGTH_SHORT).show();
        }//end if statement
        //if the user has actually entered an email address, go through the reset process
        else {
            //using Firebase authentication to send the password reset email
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //if the task is successful, lets the user know to check their emails
                    //then redirects them to the main login page
                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPassword.this, "Password Reset Email Sent!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ForgotPassword.this, LoginPage.class));
                    }//end if statement
                    //if Firebase fails to send the email for any reason, displays an error to the user
                    else {
                        Toast.makeText(ForgotPassword.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }//end else statement
                }//end onComplete method
            });//end onCompleteListener
        }//end else statement
    }//end forgotPassword
}//end class
