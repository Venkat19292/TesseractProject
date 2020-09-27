package com.task.module_orientation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/*
*
    Singleton class for accessing sensor data
*
*/
public class MainSensorData {

    private static final MainSensorData ourInstance = new MainSensorData();
    IMyAidlSensorInterface interfaceAIDl;

    public static MainSensorData getInstance() {
        return ourInstance;
    }

    private MainSensorData() {
    }

    //starting bound service
    public void initSensor(Context context) {
        Intent intent = new Intent(context, SensorService.class);
        context.bindService(intent, myCon, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection myCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            interfaceAIDl = IMyAidlSensorInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public double getSensorData(){
        double returnValue = 0;
        try {
            returnValue= interfaceAIDl.getSensorData();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

}
