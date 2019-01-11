package com.example.mayukh.instagramclone_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //private Toolbar toolbar;
    private EditText edtEmail,edtUserName,edtPassword;
    private Button btnSignUp,btnLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        edtEmail = findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(SignUp.this);
        btnLogIn.setOnClickListener(SignUp.this);
        if(ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Sign Up");
    }

    @Override
    public void onClick(View buttonSignLogin) {

        switch (buttonSignLogin.getId()){
            case R.id.btnSignUp:
                signUpUser();
                break;
            case R.id.btnLogIn:
                logInUser();
                break;

        }
    }
    public void signUpUser() {
        try {
            final ParseUser appUser = new ParseUser();
            appUser.setEmail(edtEmail.getText().toString());
            appUser.setUsername(edtUserName.getText().toString());
            appUser.setPassword(edtPassword.getText().toString());
            final ProgressDialog progressDialog =new ProgressDialog(this);
            progressDialog.setMessage("Signing Up "+edtUserName.getText().toString());
            progressDialog.show();
            appUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                if(e == null){
                    FancyToast.makeText(SignUp.this,appUser.get("username")+" signed up successfully",FancyToast.LENGTH_LONG
                            ,FancyToast.SUCCESS,true).show();
                }
                else{
                    FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG
                            ,FancyToast.ERROR,true).show();
                }
                progressDialog.dismiss();
                }
            });
        }
        catch (Exception e){
            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG
                    ,FancyToast.ERROR,true).show();
        }
    }

    public void logInUser(){

                    Intent intent = new Intent(SignUp.this,LogIn.class);
                    startActivity(intent);





    }
}

