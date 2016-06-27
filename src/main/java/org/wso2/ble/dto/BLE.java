package org.wso2.ble.dto;

/**
 * Created by ace on 6/27/16.
 */
public class BLE {
    int id;
    long timeStamp;
    String location;

    public BLE(int id, String location){
        this.id = id;
        this.location = location;
        timeStamp = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
