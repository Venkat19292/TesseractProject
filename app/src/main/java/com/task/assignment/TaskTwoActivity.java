package com.task.assignment;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.task.module_orientation.MainSensorData;

public class TaskTwoActivity extends AppCompatActivity {

    ISensorAIDL interfaceAIDl;
    private Button btn_getVal;
    private TextView txt_returnVal;


    //Sensor variables
    private SensorManager mSensorManager;
    private Sensor mRotationSensor;

    // interval 8ms
    private static final int SENSOR_DELAY = 8 * 1000;
    private static final int FROM_RADS_TO_DEGS = -57;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_two);

        initControlls();
    }

    private void initControlls() {
        btn_getVal = findViewById(R.id.btn_getVal);
        txt_returnVal = (TextView) findViewById(R.id.txt_returnVal);


        final MainSensorData mainObj = MainSensorData.getInstance();
        mainObj.initSensor(TaskTwoActivity.this);

        btn_getVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String val = String.valueOf(mainObj.getSensorData());
                    txt_returnVal.setText("Pitch -"+val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });





/*
        //starts bind service
        Intent intent =new Intent(TaskTwoActivity.this, SensorService.class);
        bindService(intent,myCon, Context.BIND_AUTO_CREATE);
*/


    }

/*
    ServiceConnection myCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            interfaceAIDl= ISensorAIDL.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
*/

}
