package com.example.mayukh.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {
    TextView txtWelcome;
    Button btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txtWelcome = findViewById(R.id.txtWelcome);
        btnLogOut = findViewById(R.id.btnLogOut);
        txtWelcome.setText("WELCOME "+ParseUser.getCurrentUser().getUsername());
        //txtWelcome.setText("WELCOME "+ParseUser.getCurrentUser().get("username"));
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(WelcomeActivity.this,SignUpLoginActivity.class);
//                startActivity(intent);
                FancyToast.makeText(WelcomeActivity.this,ParseUser.getCurrentUser().get("username") +" Logged out succesfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                ParseUser.logOut();//this will logout the user from parse
                finish();//this method will close the current activity and move back to previous activity

            }
        });

    }
}
