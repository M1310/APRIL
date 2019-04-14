package com.example.mwils.april;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Admin extends AppCompatActivity {

    //creating a variable to hold the btnCreateUser
    private Button btnCreateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        setUpFields();
        setUpOnClicks();
    }//end onCreate method

    /**
     * This method assigns the button elements on the page
     * to variables to be used for onclick events
     */
    private void setUpFields(){
        //assigning btnCreateUser to the Create User button on the admin page
        btnCreateUser = findViewById(R.id.btnCreateUser);
    }//end setUpFields

    /**
     * This method sets the onClick event to navigate the user
     * through the application
     */
    private void setUpOnClicks(){
        //setting an onclicklistener to navigate the user to the correct page
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, CreateUser.class));
            }//end onClick method
        });//end onClickListener
    }//end setUpOnClicks
}//end class
