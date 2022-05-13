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
       // getSelectedShape();
        //setValues();
        setupData();
        setUpList();
       // setUpOnclickListener();

    }

    private void setupData() {

       Item theitem = new Item("Hello1", "Hello2", "Hello3", "Hello4");
        itemList.add(theitem);
    }

    public void itemCreator(String name, String type, String description , String date ){
        Item newItem = new Item(name, type, description, date);
        itemList.add(newItem);
    }
    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.itemList);

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, itemList);
        listView.setAdapter(adapter);
    }





    private void getSelectedShape()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");

    }


    private void setValues()
    {

    }


}