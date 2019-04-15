package com.example.mwils.april;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;





public class LoginPage extends AppCompatActivity {
    //Initialising the login fields
    private EditText userName, userPassword;
    //Initialising login button
    private Button btnLogin;
    //Initialising Reset Password link
    private TextView tvReset;
    //Creating instance of Firebase authenticator
    private FirebaseAuth auth;

    private String currentUser;

    //Calling the SharedPreferences variables
    //to hold global information about the user
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //assigning the variables to the sharedprefences to set the global variables
        shared = getApplicationContext().getSharedPreferences("YourSessionName",MODE_PRIVATE);
        editor = shared.edit();


        //assigns the fields to variables that'll be passed through
        setupFields();
        setUpOnClicks();
    }//end method onCreate

    /**
     * Method used to check if either login fields are empty, and then calls method to validate credentials
     */
    private void login(){
        //Calling instance inside onCreate method
        auth = FirebaseAuth.getInstance();

        //Gathering text from login fields
        String name = userName.getText().toString().trim();
        String password = userPassword.getText().toString();

        //If statement to check if either are empty
        if(name.isEmpty() || password.isEmpty()){
            //if either are empty, toast message displays asking for both details
            Toast.makeText(this, "Please enter all login details", Toast.LENGTH_SHORT).show();
            }else{
            //if both are not empty, runs method to validate credentials
            validate(name, password);
            }//end if else statement

    }//end method login

    /**
     * Method to assign variables to page element
     */
    private void setupFields(){
        userName = findViewById(R.id.ptUsername);
        userPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        tvReset = findViewById(R.id.tvForgotPass);

    }//end method setupFields

    /**
     * Sets up on the onClick events for the button on the page
     */
    private void setUpOnClicks(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                login();
            }//end onClick
        });//end btnLogin onClickListener
        //navigating the user to the Password Reset page
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, ForgotPassword.class));
            }//end onClick method
        });//end onClickListener
    }//end setUpOnClicks

    /**
     * Method to validate login details and log into application
     * @param userName - the user's username (typically an email)
     * @param password - the user's password
     */
    private void validate(String userName, String password){
        //takes the user credentials and checks them against the Database
        auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   //if authentication is successful, inform user and send them to main page
                   Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                   //Once the user has successfully logged in, the next few lines take the user's email address
                   //and set it as a globally accessible variable to be used on other pages
                   currentUser = auth.getCurrentUser().getEmail();
                   editor.putString("currentUser",currentUser);
                   editor.commit();
                   startActivity(new Intent(LoginPage.this, MainActivity.class));
               }else{
                   //if authentication fails, display message informing user
                   Toast.makeText(LoginPage.this, "Login Unsuccessful: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
               }//end if else statment
            }//end onCompleteListener
        });//end createUser
    }//end validate method


}//end LoginPage class
