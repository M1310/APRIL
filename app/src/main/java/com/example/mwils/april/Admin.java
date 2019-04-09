package com.example.mwils.april;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mwils.april.Nutrition.Nutrition;

public class Admin extends AppCompatActivity {

    //creating a variable to hold the btnCreateUser
    private Button btnCreateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //assigning btnCreateUser to the Create User button on the admin page
        btnCreateUser = findViewById(R.id.btnCreateUser);

        //setting an onclicklistener to navigate the user to the correct page
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, CreateUser.class));
            }//end onClick method
        });//end onClickListener
    }//end onCreate method
}//end class
