package com.example.o4siang.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
    private TextView text_x;
    private TextView text_y;
    private TextView text_z;
    private SensorManager aSensorManager;
    private Sensor aSensor;
    private float gravity[] = new float[3];
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        text_x = (TextView)findViewById(R.id.TextView01);
        text_y = (TextView)findViewById(R.id.TextView02);
        text_z = (TextView)findViewById(R.id.TextView03);

        aSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        aSensorManager.registerListener(this, aSensor, aSensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
// TODO Auto-generated method stub

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
// TODO Auto-generated method stub
        gravity[0] = event.values[0];
        gravity[1] = event.values[1];
        gravity[2] = event.values[2];
        text_x.setText("X = "+gravity[0]);
        text_y.setText("Y = "+gravity[1]);
        text_z.setText("Z = "+gravity[2]);
    }
    @Override
    protected void onPause()
    {
// TODO Auto-generated method stub
/* 取消註冊SensorEventListener */
        aSensorManager.unregisterListener(this);
        Toast.makeText(this, "Unregister accelerometerListener", Toast.LENGTH_LONG).show();
        super.onPause();
    }
}
