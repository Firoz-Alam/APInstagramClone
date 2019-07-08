package com.example.apinstagramclone;

import android.os.Bundle;
import android.os.PersistableBundle;
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

public class SignUpLogginActivity extends AppCompatActivity {
    private EditText siginUpNameET,signUpPasswordET,loginNameET,loginPasswordET;
    private Button signUpbtn,loginbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_loggin_activity);

        siginUpNameET = findViewById(R.id.signUpNameET);
        signUpPasswordET = findViewById(R.id.signUpPasswordET);
        loginNameET = findViewById(R.id.loginNameET);
        loginPasswordET = findViewById(R.id.loginPasswordET);

        signUpbtn = findViewById(R.id.signUpbtn);
        loginbtn = findViewById(R.id.loginbtn);

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseUser signUp = new ParseUser();
                signUp.setUsername(siginUpNameET.getText().toString());
                signUp.setPassword(signUpPasswordET.getText().toString());

                signUp.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){

                            FancyToast.makeText(SignUpLogginActivity.this,signUp.get("username")+" is signed Up Successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }else {
                            FancyToast.makeText(SignUpLogginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               ParseUser.logInInBackground(loginNameET.getText().toString(), loginPasswordET.getText().toString(), new LogInCallback() {
                   @Override
                   public void done(ParseUser user, ParseException e) {
                       if (user != null && e == null){
                           FancyToast.makeText(SignUpLogginActivity.this,user.get("username")+" is Loggin Successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                       }else {
                           FancyToast.makeText(SignUpLogginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                       }
                   }
               });
            }
        });





    }
}
