package com.task.module_orientation;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class SensorService extends Service implements SensorEventListener {

    //Sensor variables
    private SensorManager mSensorManager;
    private Sensor mRotationSensor;

    // interval 8ms
    private static final int SENSOR_DELAY = 8 * 1000;
    private static final int FROM_RADS_TO_DEGS = -57;
    private double pitch=0;



    public SensorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        initSensor();
        return mBinder;
    }

    private void initSensor() {

        try {
            mSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
            assert mSensorManager != null;
            mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            mSensorManager.registerListener(this, mRotationSensor, SENSOR_DELAY);
        } catch (Exception e) {
            Toast.makeText(this, "Hardware compatibility issue", Toast.LENGTH_LONG).show();
        }
    }

    IMyAidlSensorInterface.Stub  mBinder = new IMyAidlSensorInterface.Stub() {
        @Override
        public double getSensorData() throws RemoteException {
            return pitch;
        }
    };


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mRotationSensor) {
            if (event.values.length > 4) {
                float[] truncatedRotationVector = new float[4];
                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4);
                update(truncatedRotationVector);
            } else {
                update(event.values);
            }
        }
    }

    //update the pitch data
    private void update(float[] vectors) {
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
        int worldAxisX = SensorManager.AXIS_X;
        int worldAxisZ = SensorManager.AXIS_Z;
        float[] adjustedRotationMatrix = new float[9];
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix);
        float[] orientation = new float[3];
        SensorManager.getOrientation(adjustedRotationMatrix, orientation);
        pitch = orientation[1] * FROM_RADS_TO_DEGS;
        //float roll = orientation[2] * FROM_RADS_TO_DEGS;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
