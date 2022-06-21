package com.example.gollect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class HomeOld extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "HomeOld";

    public ArrayList<Collection> collectionList = new ArrayList<Collection>();

    private ListView listView;

    private TextView addCollectionButton;

    private Button settingsButton;
    public Button optionsButton1;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
// ...
boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Gollect);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_old);

        mAuth = FirebaseAuth.getInstance();
        listView = (ListView) findViewById(R.id.collectionList);
        CollectionAdapter adapter = new CollectionAdapter(getApplicationContext(), 0, collectionList);
        listView.setAdapter(adapter);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userCollections = ref.child("user-collections").child(mAuth.getUid());
        userCollections.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                collectionList.clear();
                for (DataSnapshot collectionSnapshot : dataSnapShot.getChildren()){

                     String cName =  collectionSnapshot.child("name").getValue(String.class);
                     String cType =  collectionSnapshot.child("type").getValue(String.class);
                     String cGoal =  collectionSnapshot.child("goal").getValue(String.class);
                    String cKey =  collectionSnapshot.child("key").getValue(String.class);
                      Collection collection = new Collection(""+cName, ""+cType, ""+cGoal,""+cKey);

                    collectionList.add(collection);

                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setUpOnclickListener();

        addCollectionButton = (Button) findViewById(R.id.addCollection);
        addCollectionButton.setOnClickListener(this);

        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(this);

        optionsButton1 = (Button) findViewById(R.id.openOptionsBtn);
        optionsButton1.setOnClickListener(this);
    }


    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Collection selectCollection = (Collection) (listView.getItemAtPosition(position));
                Intent showCollection = new Intent(getApplicationContext(), SelectedCollectionPage.class);
                showCollection.putExtra("key",selectCollection.getKey());
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
            case R.id.settingsButton:
                startActivity(new Intent(this, Settings.class));
                break;
            case  R.id.openOptionsBtn:
                startActivity(new Intent(this, NewOptionsMenu.class));
        }
    }
}