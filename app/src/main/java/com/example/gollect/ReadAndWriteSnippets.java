package com.example.gollect;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.Map;

public class ReadAndWriteSnippets {

    private static final String TAG = "ReadAndWriteSnippets";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    public ReadAndWriteSnippets(DatabaseReference database) {
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]
    }

    // [START rtdb_write_new_user]
    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
    // [END rtdb_write_new_user]

    public void writeNewUserWithTaskListeners(String userId, String name, String email) {
        User user = new User(name, email);

        // [START rtdb_write_new_user_task]
        mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });
        // [END rtdb_write_new_user_task]
    }

    private void addCollectionEventListener(DatabaseReference mCollectionReference) {
        // [START collection_value_event_listener]
        ValueEventListener collectionListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Collection object and use the values to update the UI
               Collection collection = dataSnapshot.getValue(Collection.class);
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Collection failed, log a message
                Log.w(TAG, "loadCollection:onCancelled", databaseError.toException());
            }
        };
        mCollectionReference.addValueEventListener(collectionListener);

        // [END post_value_event_listener]
    }

    // [START write_fan_out]
    private void writeNewCollection(String userId, String name, String type, int goal) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Collection collection = new Collection(userId, name, type, goal);
        Map<String, Object> collectionValues = collection.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/collections/" + key, collectionValues);
        childUpdates.put("/user-collections/" + userId + "/" + key, collectionValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]

    private String getUid() {
        return "";
    }


    // [START post_stars_increment]
    private void onStarClicked(String uid, String key) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("posts/"+key+"/stars/"+uid, true);
        updates.put("posts/"+key+"/starCount", ServerValue.increment(1));
        updates.put("user-posts/"+uid+"/"+key+"/stars/"+uid, true);
        updates.put("user-posts/"+uid+"/"+key+"/starCount", ServerValue.increment(1));
        mDatabase.updateChildren(updates);
    }
    // [END post_stars_increment]

}