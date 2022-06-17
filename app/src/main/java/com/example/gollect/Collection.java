package com.example.gollect;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Collection {

    public String name;
    public String type;
    public String key;
    public String goal;
    public String id;
    public Map<String, Boolean> stars = new HashMap<>();

    public Collection() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Collection(String name, String type, String goal) {
        this.name = name;
        this.type = type;
        this.goal = goal;
        this.key ="";
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("type", type);
        result.put("goal", goal);

        return result;
    }

    public void SetKey(String key){
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String name) {
        this.goal = name;
    }


}
