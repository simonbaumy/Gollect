package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ListView;


import java.util.ArrayList;

import java.util.Collection;

public class SelectedCollectionPage extends AppCompatActivity {

    Collection selectedCollection;

    public static ArrayList<Item> itemList = new ArrayList<Item>();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_collection_page);
        getSelectedShape();
        setValues();

    }

    private void getSelectedShape()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");

    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.collectionList);

        ItemAdapter adapter = new  ItemAdapter(getApplicationContext(), 0,  itemList);
        listView.setAdapter(adapter);
    }

    private void setValues()
    {

    }


}