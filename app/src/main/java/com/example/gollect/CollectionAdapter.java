package com.example.gollect;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class CollectionAdapter extends ArrayAdapter<Collection> {



    private static final String TAG = "CollectionAdapter";


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

 private int currentSize;

    public String cKey;

    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

    public CollectionAdapter(Context context, int resource, List<Collection> collectionList)
    {
        super(context,resource,collectionList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Collection collection = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.collection_cell, parent, false);
        }
        TextView tv1 = (TextView) convertView.findViewById(R.id.collectionName);
        TextView tv2 = (TextView) convertView.findViewById(R.id.collectionGoal);
        TextView tv3 = (TextView) convertView.findViewById(R.id.collectionType);

        cKey = collection.getKey();
        DatabaseReference fbDb = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //Firebase.setAndroidContext(this);

        if (fbDb == null) {
            fbDb = FirebaseDatabase.getInstance().getReference();
        }
        DatabaseReference userCollections = fbDb.child("user-collections").child(mAuth.getUid()).child(cKey).child(cKey);
        userCollections
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int size = (int) dataSnapshot.getChildrenCount();

                        currentSize = size;
                        collection.setCurrentGoal(""+currentSize);

                        Log.i(TAG, "Size: " + collection.getCurrentGoal());
                        tv1.setText(collection.getName());
                        tv2.setText(  collection.getCurrentGoal() + "/ "+collection.getGoal());
                        tv3.setText(collection.getType());

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });









        return convertView;
    }
}
