package com.mpo.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnetic;

    private float[] rotationMatrix;
    private float[] accel;
    private float[] mag;
    private float[] orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Balls(this));
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        rotationMatrix = new float[16];
        accel = new float[3];
        mag = new float[3];
        orientation = new float[3];

    }
    @Override
    protected void onResume() {
        sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, mMagnetic, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        loadSensorData(sensorEvent);
        SensorManager.getRotationMatrix(rotationMatrix, null, accel, mag);
        SensorManager.getOrientation(rotationMatrix, orientation);

        Balls.Vx = (int) Math.round(Math.toDegrees(orientation[2]));
        Balls.Vy = (-1) * ((int) Math.round(Math.toDegrees(orientation[1])));
    }

    private void loadSensorData(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER){
            accel = sensorEvent.values.clone();
        }
        if (type == Sensor.TYPE_MAGNETIC_FIELD){
            mag = sensorEvent.values.clone();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}