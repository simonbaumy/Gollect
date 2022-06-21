package com.example.gollect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView registerUser;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextPasswordConfirm;
    private ProgressBar progressBar;
    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    private FirebaseAuth mAuth;
    private Button cancelReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Gollect);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.register_button);
        registerUser.setOnClickListener(this);

        editTextUsername = (EditText) findViewById(R.id.register_username);
        editTextEmail = (EditText) findViewById(R.id.register_email);
        editTextPassword = (EditText) findViewById(R.id.register_password);
        editTextPasswordConfirm = (EditText) findViewById(R.id.register_passwordConfirm);

        cancelReturn = (Button) findViewById(R.id.cancelRegis);
        cancelReturn.setOnClickListener(this);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_button:
                registerUser();
                break;
            case R.id.cancelRegis:
                Cancel();
                break;
        }
    }

    private void Cancel(){
        startActivity(new Intent(this, MainActivity.class));
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

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    User user = new User(username, email);
                    FirebaseDatabase.getInstance("https://opsc-auth-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if
                                    (task.isSuccessful()){
                                        Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        startActivity(new Intent(Register.this, MainActivity.class));
                                    }
                                    else
                                    {
                                        Toast.makeText(Register.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });
                }
                else{
                    Toast.makeText(Register.this, "Failed to register. Try Again!a", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });

    }
}