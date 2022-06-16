package com.example.gollect;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Collection {

    public String uid;
    public String name;
    public String type;
    public int goal;
    public int starCount = 0;
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
        result.put("author", name);
        result.put("title", type);
        result.put("body", goal);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }
}
