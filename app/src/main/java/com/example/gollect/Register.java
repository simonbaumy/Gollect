package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView registerUser;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextPasswordConfirm;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.register_button);
        registerUser.setOnClickListener(this);

        editTextUsername = (EditText) findViewById(R.id.register_username);
        editTextEmail = (EditText) findViewById(R.id.register_email);
        editTextPassword = (EditText) findViewById(R.id.register_password);
        editTextPasswordConfirm = (EditText) findViewById(R.id.register_passwordConfirm);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);




    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_button:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordConfirm = editTextPasswordConfirm.getText().toString().trim();

        if(username.isEmpty()){
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }

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

        if(password.length() < 6){
            editTextPassword.setError("Password has to be atleast 6 characters long");
            editTextPassword.requestFocus();
            return;
        }

        if(!passwordConfirm.equals(password)){
            editTextPasswordConfirm.setError("Password do not match");
            editTextPasswordConfirm.requestFocus();
            return;
        }


    }
}