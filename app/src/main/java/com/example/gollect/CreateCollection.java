package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CreateCollection extends AppCompatActivity {


    private TextView CreateItem;
    private EditText editTextCollectionName, editTextCollectionType, EditTextCollectionGoal;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);

        editTextCollectionName = (EditText) findViewById(R.id.register_username);
        editTextCollectionType = (EditText) findViewById(R.id.register_email);
        EditTextCollectionGoal = (EditText) findViewById(R.id.register_password);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }
}