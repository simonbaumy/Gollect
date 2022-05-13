package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeOld extends AppCompatActivity {


    public static ArrayList<GollectCollection> collectionList = new ArrayList<GollectCollection>();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_old);

        setupData();
        setUpList();
       // setUpOnclickListener();
    }

    private void setupData()
    {
        GollectCollection circle = new GollectCollection("2022 Hits", "Movies", "5");
        collectionList.add(circle);




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
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Collections selectCollection = (Collections) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id",selectCollection.getId());
                startActivity(showDetail);
            }
       }); */

    }
}