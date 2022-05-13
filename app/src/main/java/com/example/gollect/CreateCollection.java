package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CreateCollection extends HomeOld  implements View.OnClickListener{


    private TextView CreateItem;
    private EditText editTextCollectionName, editTextCollectionType, editTextCollectionGoal;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);

        editTextCollectionName = (EditText) findViewById(R.id.CreateCollectionName);
        editTextCollectionType = (EditText) findViewById(R.id.CreateCollectionType);
        editTextCollectionGoal = (EditText) findViewById(R.id.CreateCollectionGoal);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.CreateCollection:
                CreateTheCollection();

                break;
        }
        
    }

    private void CreateTheCollection() {

        String cName = editTextCollectionName.getText().toString().trim();
        String cType = editTextCollectionType.getText().toString().trim();
        String cGoal = editTextCollectionGoal.getText().toString().trim();

        if(cName.isEmpty()){
            editTextCollectionName.setError("Collection name is required");
            editTextCollectionName.requestFocus();
            return;
        }
        if(cType.isEmpty()){
            editTextCollectionName.setError("Collection type is required");
            editTextCollectionName.requestFocus();
            return;
        }
        if(cGoal.isEmpty()){
            editTextCollectionName.setError("Collection goal is required");
            editTextCollectionName.requestFocus();
            return;
        }

        CollectionCreator(cName, cGoal, cType);
        startActivity(new Intent(CreateCollection.this, HomeOld.class));

    }
}