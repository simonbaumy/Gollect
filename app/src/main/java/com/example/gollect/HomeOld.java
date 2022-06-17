package com.example.gollect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class HomeOld extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "HomeOld";

    public ArrayList<Collection> collectionList = new ArrayList<Collection>();

    private ListView listView;

    private TextView addCollectionButton;

    private Button settingsButton;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_old);
        mAuth = FirebaseAuth.getInstance();
        listView = (ListView) findViewById(R.id.collectionList);
        CollectionAdapter adapter = new CollectionAdapter(getApplicationContext(), 0, collectionList);
        listView.setAdapter(adapter);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userCollections = ref.child("user-collections").child("g7ZbjcYGN9cmqgqhGTZquz41ioO2");
        userCollections.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                collectionList.clear();
                for (DataSnapshot collectionSnapshot : dataSnapShot.getChildren()){
                    Log.d(TAG, collectionSnapshot.child("name").getValue(String.class));
                     String cName =  collectionSnapshot.child("name").getValue(String.class);
                     String cType =  collectionSnapshot.child("name").getValue(String.class);
                     String cGoal =  collectionSnapshot.child("name").getValue(String.class);
                      Collection collection = new Collection(""+cName, ""+cType, ""+cGoal);
                    Log.d(TAG, collection.name);
                    collectionList.add(collection);

                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setUpList();
        setUpOnclickListener();

        addCollectionButton = (Button) findViewById(R.id.addCollection);
        addCollectionButton.setOnClickListener(this);

        settingsButton = (Button) findViewById(R.id.settingsBtn);
    }


    private void setUpList()
    {




    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Collection selectCollection = ( Collection) (listView.getItemAtPosition(position));
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