package com.example.gollect;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Collection {

    public String uid;
    public String name;
    public String type;
    public int goal;
    public Map<String, Boolean> stars = new HashMap<>();

    public Collection() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Collection(String uid, String name, String type, int goal) {
        this.uid = uid;
        this.name = name;
        this.type = type;
        this.goal = goal;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("type", type);
        result.put("goal", goal);

        return result;
    }
}
