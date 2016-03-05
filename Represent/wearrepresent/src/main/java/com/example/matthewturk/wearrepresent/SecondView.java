package com.example.matthewturk.wearrepresent;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Created by matthewturk on 3/3/16.
 */
public class SecondView extends WearableActivity {
    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;
    ImageView imageV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        setAmbientEnabled();
        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
        sendIntent.putExtra("CAT_NAME", "Jorg Mason");
        startService(sendIntent);
        addListenerOnButton();

//        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
//        mTextView = (TextView) findViewById(R.id.text);
//        mClockView = (TextView) findViewById(R.id.clock);
    }

    public void getOtherView(SecondView v) {
        Intent intent = new Intent(getApplicationContext(), FirstSecondView.class);
        startActivity(intent);
    }
    public void getNextView(SecondView v) {
        Intent intent = new Intent(getApplicationContext(), PollView.class);
        startActivity(intent);
    }
    public void addListenerOnButton() {
        imageV = (ImageView) findViewById(R.id.imageView3);
        imageV.setOnTouchListener(new OnSwipeTouchListener(SecondView.this) {
            @Override
            public void onSwipeTop() {
                Toast.makeText(SecondView.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(SecondView.this, "right", Toast.LENGTH_SHORT).show();
                getOtherView(SecondView.this);
            }
            public void onSwipeLeft() {
                Toast.makeText(SecondView.this, "left", Toast.LENGTH_SHORT).show();
                getNextView(SecondView.this);
            }
            public void onSwipeBottom() {
                Toast.makeText(SecondView.this, "bottom", Toast.LENGTH_SHORT).show();

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
