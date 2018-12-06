package com.example.mwils.april_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    //Initialising the login fields
    private EditText userName, userPassword;
    //Initialising login button
    private Button btnLogin;
    //Creating instance of Firebase authenticator
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Calling instance inside onCreate method
        auth = FirebaseAuth.getInstance();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(filled()){
                    //Upload Data to Firebase
                    //Gathering username and password, and trimming whitespace
                    String name = userName.getText().toString().trim();
                    String password = userPassword.getText().toString().trim();

                    
                }
            }//end onClick
        });//end btnLogin onClickListener

    }//end method onCreate

    //Method used to check if either login fields are empty
    private Boolean filled(){
        //Setting boolean flag
        Boolean result = false;

        //Gathering text from login fields
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();

        //If statement to check if either are empty
        if(name.isEmpty() && password.isEmpty()){
            //if either are empty, toast message displays asking for both details
            Toast.makeText(this, "Please enter all login details", Toast.LENGTH_SHORT).show();
            }else{
            //if both are not empty, no toast message appears
            result = true;
            }//end if else statement

        return result;
    }//end method validate
}//end LoginPage class
