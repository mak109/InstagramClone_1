package com.example.mayukh.instagramclone_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
                if(edtLogInEmail.getText().toString().equals("") || edtLogInPassword.getText().toString().equals("")){
                    FancyToast.makeText(LogIn.this,"Password/Email Id required",FancyToast.LENGTH_LONG,
                            FancyToast.INFO,true).show();
                }
                else
                logIn();
            }
        });

        btnSignUpnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });
        if(ParseUser.getCurrentUser()!=null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
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
                    transitionToSocialMediaActivity();
                }
                else{
                    FancyToast.makeText(LogIn.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }


        });




    }
    public void rootLayoutloginTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LogIn.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();

    }
}

