package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView registerUser;
    private EditText username, email, password, passwordConfirm;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.register_button);
        registerUser.setOnClickListener(this);

        username = (EditText) findViewById(R.id.register_username);
        email= (EditText) findViewById(R.id.register_email);
        password = (EditText) findViewById(R.id.register_password);
        passwordConfirm = (EditText) findViewById(R.id.register_passwordConfirm);

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



    }
}