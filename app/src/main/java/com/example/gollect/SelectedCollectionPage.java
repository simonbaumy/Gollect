package com.example.gollect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectedCollectionPage extends AppCompatActivity implements View.OnClickListener {

    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

    private ListView listView;

    private TextView addItemButton;
    private FirebaseAuth mAuth;

    private Button cancelMenu;
    private Button graph;
    public ArrayList<Item> itemList = new ArrayList<>();
    private static final String TAG = "SelectedCollectionPage";



    private DatabaseReference mDatabase;

    public String key;

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



        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        listView = (ListView) findViewById(R.id.itemList);
        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, itemList);
        listView.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference userItems = ref.child("user-collections").child(mAuth.getUid()).child(key).child(key);
        userItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                itemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapShot.getChildren()){
                   //Log.d(TAG, itemSnapshot.child("itemName").getValue(String.class));
                    String cName =  itemSnapshot.child("itemName").getValue(String.class);
                    String cType =  itemSnapshot.child("itemType").getValue(String.class);
                    String cDate =  itemSnapshot.child("itemDate").getValue(String.class);
                    String cDescription =  itemSnapshot.child("itemDescription").getValue(String.class);
                    com.example.gollect.Item item = new com.example.gollect.Item(""+cName, ""+cType, ""+cDate, ""+cDescription);
                   // Log.d(TAG, item.itemName);
                    itemList.add(item);

                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       // setUpOnclickListener();


        addItemButton = (Button) findViewById(R.id.addItemBtn);
        addItemButton.setOnClickListener(this);

        cancelMenu = (Button)  findViewById(R.id.selColReturnToMain);
        cancelMenu.setOnClickListener(this);

        graph = (Button) findViewById(R.id.graphBtn);
        graph.setOnClickListener(this);
    }


    //public void itemCreator(String name ,String type, String description, String date, String imageID){
    //    Item newItem = new Item(name, type, description, date);
     //   itemList.add(newItem);
   // }



    private void getSelectedCollectionID()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addItemBtn:

                Intent intent = getIntent();
                String cKey = intent.getStringExtra("key");

                Intent showItem = new Intent(getApplicationContext(), CreateItemPage.class);
                showItem.putExtra("key",cKey);
                startActivity(showItem);

               // startActivity(new Intent(this, CreateItemPage.class));
                break;
            case R.id.selColReturnToMain:
                ReturnToMain();
                break;
            case R.id.graphBtn:
                ShowGraph();
                break;

        }
    }

    private void ReturnToMain(){
            startActivity(new Intent(this, HomeOld.class));

    }

    private void ShowGraph(){

        Intent intent = getIntent();
        String cKey = intent.getStringExtra("key");

        Intent showItem = new Intent(getApplicationContext(), Graph.class);
        showItem.putExtra("key",cKey);
        startActivity(showItem);

    }
}