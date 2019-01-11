package com.example.mayukh.instagramclone_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogIn extends AppCompatActivity {
    Button btnLogInActivity,btnSignUpnew;
    EditText edtLogInEmail,edtLogInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setTitle("Log In");
        btnLogInActivity = findViewById(R.id.btnLogInActivity);
        btnSignUpnew = findViewById(R.id.btnSignUpnew);

        edtLogInEmail = findViewById(R.id.edtLogInEmail);
        edtLogInPassword = findViewById(R.id.edtLogInPassword);

        btnLogInActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        btnSignUpnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                finish();
            }
        });
        if(ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }
    }



    public void logIn(){

        ParseUser.logInInBackground(edtLogInEmail.getText().toString(), edtLogInPassword.getText()
                .toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user!=null && e==null){
                    FancyToast.makeText(LogIn.this,user.get("username")+
                            " is successfully logged in", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                }
                else{
                    FancyToast.makeText(LogIn.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }


        });




    }
}

