package com.example.mwils.april;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;




public class LoginPage extends AppCompatActivity {
    //Initialising the login fields
    private EditText userName, userPassword;
    //Initialising login button
    private Button btnLogin;
    //Initialising logo variable
    private ImageView imgLogo;
    //Creating instance of Firebase authenticator
    private FirebaseAuth auth;
    //Creating variable for FirebaseStorage
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    /**
     * Creates instance of LoginPage
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //assigns the fields to variables that'll be passed through
        setupFields();

        //Calling instance inside onCreate method
        auth = FirebaseAuth.getInstance();

        //Checking if User has already logged in
        FirebaseUser user = auth.getCurrentUser();
        if (user!= null){
            //finish method that resets the current activities state
            finish();
            //if the user has already logged in, send them to the main page
            startActivity(new Intent(LoginPage.this, MainActivity.class));
        }//end if statement

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
               login();
            }//end onClick
        });//end btnLogin onClickListener

        StorageReference storageRef = storage.getReference();

        StorageReference logoRef = storageRef.child("logo.png");


        logoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("mytag","got the download URL");
                String test = uri.toString();

                Context context = getApplicationContext();
                Glide.with(context)
                        .load(test)
                        .into(imgLogo);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("mytag","didn't get it lad, sorry");
            }
        });



    }//end method onCreate

    /**
     * Method used to check if either login fields are empty, and then calls method to validate credentials
     * @return
     */
    private void login(){

        //Gathering text from login fields
        String name = userName.getText().toString();
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
        imgLogo = findViewById(R.id.logo);

    }//end method setupFields

    /**
     * Method to validate login details and log into application
     * @param userName
     * @param password
     */
    private void validate(String userName, String password){
        //takes the user credentials and checks them against the Database
        auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   //if authentication is successful, inform user and send them to main page
                   Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(LoginPage.this, MainActivity.class));
               }else{
                   //if authentication fails, display message informing user
                   Toast.makeText(LoginPage.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
               }//end if else statment
            }//end onCompleteListener
        });//end createUser
    }//end validate method


}//end LoginPage class
