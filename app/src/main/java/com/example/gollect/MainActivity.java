package com.example.gollect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView login_Create;

    private EditText editTextEmail, editTextPassword;

    private Button buttonSignin;

    public Settings settings;

    private FirebaseAuth mAuth;
    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Gollect);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_Create = (TextView)findViewById(R.id.login_Create);
        login_Create.setOnClickListener(this);

        buttonSignin = (Button) findViewById(R.id.signIn_button);
        buttonSignin.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.login_email);
        editTextPassword = (EditText) findViewById(R.id.login_password);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_Create:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.signIn_button:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;

        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                        startActivity(new Intent(MainActivity.this, HomeOld.class));

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials.", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }

            }
        });
    }
}