package com.example.carl.bluedemo;

public class Blue {
    private String blueName;
    private String mac;
    private int imageId;

    public Blue(String blueName, String mac, int imageId) {
        this.blueName = blueName;
        this.mac = mac;
        this.imageId = imageId;
    }

    public String getBlueName() {
        return blueName;
    }

    public String getMac() {
        return mac;
    }

    public int getImageId() {
        return imageId;
    }
}
