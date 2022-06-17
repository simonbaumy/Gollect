package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import java.util.Collection;

public class SelectedCollectionPage extends AppCompatActivity implements View.OnClickListener {

    Collection selectedCollection;
    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    public static ArrayList<Item> itemList = new ArrayList<Item>();

    private ListView listView;

    private TextView addItemButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (getMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Gollect);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_collection_page);

        //setValues();
        setupData();
        setUpList();
       // setUpOnclickListener();


        addItemButton = (Button) findViewById(R.id.addItemBtn);
        addItemButton.setOnClickListener(this);
    }

    private void setupData() {


    }

    public void itemCreator(String name ,String type, String description, String date, String imageID){
        Item newItem = new Item(name, type, description, date, imageID);
        itemList.add(newItem);
    }
    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.itemList);

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, itemList);
        listView.setAdapter(adapter);
    }





    private void getSelectedCollectionID()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");

    }


    private void setValues()
    {

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addItemBtn:
                startActivity(new Intent(this, CreateItemPage.class));
                break;
        }
    }
}