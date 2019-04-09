package com.example.mwils.april;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ForgotPassword extends AppCompatActivity {

    //Initialising the Button variable
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //assigning btnReset variable to the reset button
        btnReset = (Button) findViewById(R.id.btnResetPass);
    }//end onCreate
}//end class
