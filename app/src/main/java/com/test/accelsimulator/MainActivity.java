package com.test.accelsimulator;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

public class MainActivity extends AppCompatActivity {
    // 定义模拟器的Sensor管理器
    private SensorManagerSimulator mSensorManager;
    // 定义界面上的文本框组件
    EditText etTxt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取程序界面的文本框组件
        etTxt1 = (EditText) findViewById(R.id.txt1);
        // 获取传感器模拟器的传感器管理服务
        mSensorManager =   SensorManagerSimulator.getSystemService(this,

                SENSOR_SERVICE);  ;
        // 连接传感器模拟器
        mSensorManager. connectSimulator();
    }
    protected void onResume()	{
        super.onResume();
        // 为系统的加速度传感器注册监听器
        mSensorManager.registerListener(new SensorEventListener() {
                                            @Override
                                            public void onAccuracyChanged(Sensor sensor, int i) {

                                            }

                                            @Override
                                            public void onSensorChanged(SensorEvent sensorEvent) {
                                                float[] values = sensorEvent.values;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append("X方向上的加速度：");
                                                sb.append(values[0]);
                                                sb.append("\nY方向上的加速度：");
                                                sb.append(values[1]);
                                                sb.append("\nZ方向上的加速度：");
                                                sb.append(values[2]);
                                                etTxt1.setText(sb.toString());
                                            }
                                        },
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onStop()	{
        // 取消注册
        mSensorManager.unregisterListener(new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

            }
        });
        super.onStop();
    }
}
