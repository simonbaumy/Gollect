package com.example.gollect;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Item {

    public String id, itemName, itemType, itemDescription, itemDate, imageID;
    public String key;
    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    public Item(String itemName, String itemType, String itemDate, String itemDescription){

        this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.itemDate = itemDate;
        this.imageID = "";
        this.key = "";

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("itemName", itemName);
        result.put("itemType", itemType);
        result.put("itemDate", itemDate);
        result.put("itemDescription", itemDate);

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
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public String getType() {
        return itemType;
    }

    public void setType(String name) {
        this.itemType = name;
    }

    public String getDescription() {
        return itemDescription;
    }

    public void setDescription(String name) {
        this.itemDescription = name;
    }
    public String getDate() {
        return itemDate;
    }

    public void setDate(String name) {
        this.itemDate = name;
    }

    public String getImageID() {
        return imageID;
    }

    public void setBitmap(String imageID) {
        this.imageID = imageID;
    }
}
