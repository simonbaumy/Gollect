package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeOld extends AppCompatActivity implements View.OnClickListener{


    public static ArrayList<GollectCollection> collectionList = new ArrayList<GollectCollection>();

    private ListView listView;

    private TextView addCollectionButton;

    private Switch themeSwitch;
    private TextView themeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme((R.style.DarkTheme));
        }else {
            setTheme((R.style.Theme_Gollect));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_old);

        themeSwitch = findViewById(R.id.themeSwitchMode);
        themeText = findViewById(R.id.themeTextView);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            themeSwitch.setChecked(true);
        }

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    themeText.setText(("Dark Mode"));
                    reset();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    themeText.setText(("Light Mode"));
                    reset();
                }
            }

            private void reset() {
                Intent intent = new Intent(getApplicationContext(), HomeOld.class);
                startActivity(intent);
                finish();
            }
        });

        setupData();
        setUpList();
        setUpOnclickListener();

        addCollectionButton = (Button) findViewById(R.id.addCollection);
        addCollectionButton.setOnClickListener(this);
    }

    private void setupData()
    {

      //  GollectCollection theNewcollection = new GollectCollection("Test", "Test2", "Test3");
        //collectionList.add(theNewcollection);

    }

    public void CollectionCreator(String name, String goal, String type ){
        GollectCollection theNewcollection = new GollectCollection(name, goal, type);
        collectionList.add(theNewcollection);
    }
    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.collectionList);

        CollectionAdapter adapter = new CollectionAdapter(getApplicationContext(), 0, collectionList);
        listView.setAdapter(adapter);
    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                GollectCollection selectCollection = ( GollectCollection) (listView.getItemAtPosition(position));
                Intent showCollection = new Intent(getApplicationContext(), SelectedCollectionPage.class);
                showCollection.putExtra("id",selectCollection.getId());
                startActivity(showCollection);
            }
       });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addCollection:
                startActivity(new Intent(this, CreateCollection.class));
                break;
        }
    }
}