package com.example.matthewturk.wearrepresent;

import android.support.wearable.activity.WearableActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Created by matthewturk on 3/3/16.
 */
public class FirstSecondView  extends WearableActivity {
    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;
    ImageView imageV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_second_layout);
        setAmbientEnabled();
//        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        sendIntent.putExtra("CAT_NAME", "Bob Monk");
//        startService(sendIntent);
        addListenerOnImage();




//        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
//        mTextView = (TextView) findViewById(R.id.text);
//        mClockView = (TextView) findViewById(R.id.clock);
    }
    public void getOtherView(FirstSecondView v) {
        Intent intent = new Intent(getApplicationContext(), SecondView.class);
        startActivity(intent);
    }
    public void addListenerOnImage() {
        imageV = (ImageView) findViewById(R.id.imageView1);
        imageV.setOnTouchListener(new OnSwipeTouchListener(FirstSecondView.this) {
            @Override
            public void onSwipeTop() {
                Toast.makeText(FirstSecondView.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(FirstSecondView.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(FirstSecondView.this, "left", Toast.LENGTH_SHORT).show();
                getOtherView(FirstSecondView.this);
            }
            public void onSwipeBottom() {
                Toast.makeText(FirstSecondView.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }

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

}

