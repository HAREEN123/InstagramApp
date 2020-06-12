package com.example.instagramapp;

// This is the Second activity, that happens when tapping the switch to the another activity button in the Initial activity..

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {// connect the sign up login XML
    private EditText edtUserNameSignUp,edtUserNameLogin,edtPasswordSignUp,edtPasswordLogin;
    private Button btnSignUp,btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity); // we are using the setContentView object to connect with the
        // Sign up login activity XML that has been created.

        edtUserNameSignUp = findViewById(R.id.edtSignUpUserName);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordSignUp = findViewById(R.id.edtSignUpPassword);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSignUp = findViewById(R.id.btnSignUpUser);
        btnLogin = findViewById(R.id.btnLogInUser);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {// Signing up to the server.
                    @Override
                    public void done(ParseException e) {
                        if(e==null){

                            FancyToast.makeText(SignUpLoginActivity.this, appUser.get("username") + " is signed up successfully.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class); // this must be put in oder to move in to the next activity window...
                            startActivity(intent);

                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }

                    }
                });



            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user != null && e == null){

                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + " is logged in successfully.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(intent); // Intent object is more important when creating activities...


                        }else{

                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }

                    }
                });
                
            }
        });

        // When the user logged in, we need to transit in to an another activity.



    }
}
