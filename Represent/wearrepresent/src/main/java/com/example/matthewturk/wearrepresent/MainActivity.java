package com.example.matthewturk.wearrepresent;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.content.Context;
import com.example.matthewturk.wearrepresent.ShakeDetector.OnShakeListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends WearableActivity {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;


    ImageView imageV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();
//        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        sendIntent.putExtra("CAT_NAME", "Bob Monk");
//        startService(sendIntent);
        // ShakeDetector initialization
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mAccelerometer = mSensorManager
//                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mShakeDetector = new ShakeDetector();
//        mShakeDetector.setOnShakeListener(new OnShakeListener() {
//
//            @Override
//            public void onShake(int count) {
//				/*
//				 * The following method, "handleShakeEvent(count):" is a stub //
//				 * method you would use to setup whatever you want done once the
//				 * device has been shook.
//				 */
//                handleShakeEvent(count);
//            }
//        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        addListenerOnImage();
        }


        public void handleShakeEvent(int count) {
            Intent intent = new Intent(getApplicationContext(), PollView.class);
            startActivity(intent);

        }

        public void getOtherView(MainActivity v) {
            Intent intent = new Intent(getApplicationContext(), FirstSecondView.class);
            startActivity(intent);
        }
        public void getNextView(MainActivity v) {
            Intent intent = new Intent(getApplicationContext(), PollView.class);
            startActivity(intent);
        }
        public void addListenerOnImage() {
            imageV = (ImageView) findViewById(R.id.imageView10);
            imageV.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
                @Override
                public void onSwipeTop() {
                    Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                }
                public void onSwipeRight() {
                    Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();

                }
                public void onSwipeLeft() {
                    Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                    getOtherView(MainActivity.this);
                }
                public void onSwipeBottom() {
                    Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();

                }

            });
        }


//        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
//        mTextView = (TextView) findViewById(R.id.text);
//        mClockView = (TextView) findViewById(R.id.clock);

//    public void getOtherView(MainActivity v) {
//        Intent intent = new Intent(getApplicationContext(), FirstSecondView.class);
//        startActivity(intent);
//    }
//    public void addListenerOnImage() {
//        imageV = (ImageView) findViewById(R.id.imageView1);
//        imageV.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
//        @Override
//        public void onSwipeTop() {
//            Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
//        }
//        public void onSwipeRight() {
//            Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
//        }
//        public void onSwipeLeft() {
//            Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
//            getOtherView(MainActivity.this);
//        }
//        public void onSwipeBottom() {
//            Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//        }
//
//        });
//    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }
    /* put this into your activity class */
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            if (mAccel > 12) {
                int rand = (int) Math.ceil(Math.random() * 100);
                String toPass = "\n" + Integer.toString(rand);
                Intent sendZip = new Intent(MainActivity.this, PollView.class);
                sendZip.putExtra("rep", toPass);
                startActivity(sendZip);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}
