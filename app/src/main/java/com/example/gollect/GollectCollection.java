package com.example.gollect;

public class GollectCollection {

    public String id, collectionName, collectionType, collectionGoal;

    public GollectCollection(){

    }

    public GollectCollection(String collectionName, String collectionType, String collectionGoal){

        this.collectionName = collectionName;
        this.collectionType = collectionType;
        this.collectionGoal = collectionGoal;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return collectionName;
    }

    public void setName(String name) {
        this.collectionName = name;
    }

    public String getType() {
        return collectionType;
    }

    public void setType(String name) {
        this.collectionType = name;
    }

    public String getGoal() {
        return collectionGoal;
    }

    public void setGoal(String name) {
        this.collectionGoal = name;
    }

}

