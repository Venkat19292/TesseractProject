package com.task.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_taskone;
    private Button btn_tasktwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControlls();
    }

    private void initControlls() {

        btn_taskone = (Button) findViewById(R.id.btn_taskone);
        btn_tasktwo = (Button) findViewById(R.id.btn_tasktwo);

        //Start first task listing all apps from device
        btn_taskone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskOnActivity.class);
                startActivity(intent);
            }
        });


        //start activity and show sensor data
        btn_tasktwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskTwoActivity.class);
                startActivity(intent);
            }
        });
    }

}
