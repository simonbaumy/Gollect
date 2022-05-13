package com.example.gollect;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class Item {

    public String id, itemName, itemType, itemDescription, itemDate, imageID;

    public Item(String itemType, String itemDate, String itemDescription, String imageID){

       // this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.itemDate = itemDate;
        this.imageID = imageID;
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
