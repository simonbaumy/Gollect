package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CreateItemPage extends SelectedCollectionPage implements View.OnClickListener {

    private TextView createItem;
    private EditText editTextItemName, editTextItemType, editTextItemDescription,editTextItemDate ;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item_page);

        editTextItemName = (EditText) findViewById(R.id.create_item_name);
        editTextItemType = (EditText) findViewById(R.id.create_item_type);
        editTextItemDescription = (EditText) findViewById(R.id.create_item_description);
        editTextItemDate = (EditText) findViewById(R.id.create_item_date);

        createItem = (Button) findViewById(R.id.CreateCollection);
        createItem.setOnClickListener(this);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.CreateCollection:
                CreateTheItem();

                break;
        }
    }

    private void CreateTheItem() {

        String iName = editTextItemName.getText().toString().trim();
        String iType = editTextItemType.getText().toString().trim();
        String iDescription = editTextItemDescription.getText().toString().trim();
        String iDate = editTextItemDate.getText().toString().trim();

        if(iName.isEmpty()){
            editTextItemName.setError("Collection name is required");
            editTextItemName.requestFocus();
            return;
        }
        if(iType.isEmpty()){
            editTextItemType.setError("Collection type is required");
            editTextItemType.requestFocus();
            return;
        }
        if(iDescription.isEmpty()){
            editTextItemDescription.setError("Collection goal is required");
            editTextItemDescription.requestFocus();
            return;
        }
        if(iDate.isEmpty()){
            editTextItemDate.setError("Collection goal is required");
            editTextItemDate.requestFocus();
            return;
        }

        itemCreator(iName, iType, iDescription, iDate);
        startActivity(new Intent(this, SelectedCollectionPage.class));

    }
}