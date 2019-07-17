package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.SolverVariable;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailET,passwordET;
    private Button loginbtn,signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);

        passwordET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {

                if (keycode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(loginbtn);
                }

                return false;
            }
        });

        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.signupbtn);

        loginbtn.setOnClickListener(LoginActivity.this);
        signupbtn.setOnClickListener(LoginActivity.this);


        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginbtn:

                if (emailET.getText().toString().equals("") || passwordET.getText().toString().equals("")){

                    FancyToast.makeText(this,"Email or Password Required.Then he/she logged in !",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }else {

                    ParseUser.logInInBackground(emailET.getText().toString(), passwordET.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {

                                FancyToast.makeText(LoginActivity.this, user.get("username") + " is Successful Log in!",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitionSocialmediaActivity();
                            } else {

                                FancyToast.makeText(LoginActivity.this, " Error !" + e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }
                break;
            case R.id.signupbtn:
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void rootLayoutTapped(View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void transitionSocialmediaActivity(){
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
