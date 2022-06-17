package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CreateCollection extends HomeOld implements View.OnClickListener{


    private TextView createCollection;
    private EditText editTextCollectionName, editTextCollectionType, editTextCollectionGoal;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);

        editTextCollectionName = (EditText) findViewById(R.id.CreateCollectionName);
        editTextCollectionType = (EditText) findViewById(R.id.CreateCollectionType);
        editTextCollectionGoal = (EditText) findViewById(R.id.CreateCollectionGoal);

        createCollection = (Button) findViewById(R.id.CreateCollection);
        createCollection.setOnClickListener(this);

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
            editTextCollectionType.setError("Collection type is required");
            editTextCollectionType.requestFocus();
            return;
        }
        if(cGoal.isEmpty()){
            editTextCollectionGoal.setError("Collection goal is required");
            editTextCollectionGoal.requestFocus();
            return;
        }

        try{
            int number = Integer.parseInt(cGoal);
        }
        catch (NumberFormatException ex){
            editTextCollectionGoal.setError("Not a Valid Goal Number. Please Input Integer");
            editTextCollectionGoal.requestFocus();
           return;
        }

        ReadAndWriteSnippets c = new ReadAndWriteSnippets();

        mAuth = FirebaseAuth.getInstance();
        c.writeNewCollection(cName, cType,  cGoal);

        startActivity(new Intent(this, HomeOld.class));

    }
}