package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeOld extends AppCompatActivity implements View.OnClickListener{


    public static ArrayList<GollectCollection> collectionList = new ArrayList<GollectCollection>();

    private ListView listView;

    private TextView addCollectionButton;

    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_old);

        setupData();
        setUpList();
        setUpOnclickListener();

        addCollectionButton = (Button) findViewById(R.id.addCollection);
        addCollectionButton.setOnClickListener(this);

        settingsButton = (Button) findViewById(R.id.settingsBtn);
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