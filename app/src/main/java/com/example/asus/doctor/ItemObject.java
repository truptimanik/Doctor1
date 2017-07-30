package com.example.asus.doctor;

/**
 * Created by ASUS on 16-07-2017.
 */

public class ItemObject {

    private String name;
    private int imageId;

    public ItemObject(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}