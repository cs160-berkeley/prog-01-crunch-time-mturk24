package com.example.matthewturk.represent;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by matthewturk on 3/1/16.
 */
public class SecondClass extends Activity {
    Button button3;
    Button button4;
    Button button5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
    }

    public void getNewView(View v) {
        Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
        startActivity(intent);
    }

//    public void addListenerOnButton() {
//
//
//        button3 = (Button) findViewById(R.id.button3);
//        button4 = (Button) findViewById(R.id.button4);
//        button5 = (Button) findViewById(R.id.button5);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (myInput.getText().length() == 5) {
//                    Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}
