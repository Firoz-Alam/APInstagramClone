package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emailET,nameET,passwordET;
    private Button signupbtn,loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign UP");

        emailET = findViewById(R.id.emailET);
        nameET = findViewById(R.id.nameET);
        passwordET = findViewById(R.id.passwordET);

        passwordET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                if (keycode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(signupbtn);
                }
                return false;
            }
        });

        signupbtn = findViewById(R.id.signupbtn);
        loginbtn = findViewById(R.id.loginbtn);

        signupbtn.setOnClickListener(MainActivity.this);
        loginbtn.setOnClickListener(MainActivity.this);

        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionSocialmediaActivity();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signupbtn:
                if (emailET.getText().toString().equals("") || nameET.getText().toString().equals("") || passwordET.getText().toString().equals("")){

                    FancyToast.makeText(this,"Email, User Name, Password Required !",
                            FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();

                }else {

                    final ParseUser signup = new ParseUser();
                    signup.setEmail(emailET.getText().toString());
                    signup.setUsername(nameET.getText().toString());
                    signup.setPassword(passwordET.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + nameET.getText().toString());
                    progressDialog.show();

                    signup.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, signup.get("username") + " is Successful Sign UP!",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                transitionSocialmediaActivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, " Error !" + e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.loginbtn:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
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
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
