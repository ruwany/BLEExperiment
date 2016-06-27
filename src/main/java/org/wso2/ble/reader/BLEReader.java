package org.wso2.ble.reader;

import org.wso2.ble.dto.BLE;
import org.wso2.ble.eventprocessor.wrapper.SidhdhiWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ace on 6/27/16.
 */
public class BLEReader implements Runnable{

    private ReadWriteLock rwlock = new ReentrantReadWriteLock();
    List<BLE> bleData = new ArrayList<BLE>();
    public volatile Thread bleReader;
    private static final int INTERVAL = 2000;

    public void run(){
        Thread thisThread = Thread.currentThread();
        while (bleReader == thisThread) {
            write();
            try {
                thisThread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        bleReader = null;
    }

    public void start(){
        bleReader = new Thread(this);
        bleReader.start();
    }

    public void write()
    {
        rwlock.writeLock().lock();
        try {
            bleData.add(new BLE(123, "loc_1"));
            bleData.add(new BLE(123, "loc_2"));
            bleData.add(new BLE(123, "loc_3"));
            SidhdhiWrapper.setBleData(bleData);
        } finally {
            rwlock.writeLock().unlock();
        }
    }
}
