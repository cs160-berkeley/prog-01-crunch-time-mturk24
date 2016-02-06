package com.example.matthewturk.caloriecount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText burnBox;
    EditText inputWeight;
    Button convertingButton;
    Button otherOptionsButton;
    EditText repsMinutesBox;
    Float newRepsMinutes;
    String newRepsMinutesParsed;
    View view;
    Float burnVal;
    Float total;
    ListView listView;
    Float appropriateRatio;
    Integer intWeight;
    HashMap<String, Float> exerciseMap;
    Integer repsMinutesInt;
    ArrayAdapter<String> mainAdapter;
    ArrayAdapter<CharSequence> adapter;
    public String itemSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        OnClickButtonListener();
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.exercise_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = spinner.getSelectedItem().toString();
                if (!(itemSelected.equals("None Selected"))) {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                    if ((itemSelected.equals("Pushup")) || (itemSelected.equals("Situp")) || (itemSelected.equals("Squats")) || (itemSelected.equals("Pullup"))) {
                        ((TextView) findViewById(R.id.minuteNumber)).setText("Number of Reps");
                    } else {
                        ((TextView) findViewById(R.id.minuteNumber)).setText("Number of Minutes");
                    }
                } else {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        exerciseMap = new HashMap<>();
        exerciseMap.put("Pushup", (float) 100 / 350);
        exerciseMap.put("Situp", (float) 100 / 200);
        exerciseMap.put("Squats", (float) 100 / 225);
        exerciseMap.put("Leg-lift", (float) 100 / 25);
        exerciseMap.put("Plank", (float) 100 / 25);
        exerciseMap.put("Jumping Jacks", (float) 100 / 10);
        exerciseMap.put("Pullup", (float) 100 / 100);
        exerciseMap.put("Cycling", (float) 100 / 12);
        exerciseMap.put("Walking", (float) 100 / 20);
        exerciseMap.put("Jogging", (float) 100 / 12);
        exerciseMap.put("Swimming", (float) 100 / 13);
        exerciseMap.put("Stair-Climbing", (float) 100 / 15);
        convertingButton = (Button) findViewById(R.id.convertButton);

        convertingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                appropriateRatio = exerciseMap.get(itemSelected);
                inputWeight = (EditText) findViewById((R.id.weightText));
                intWeight = Integer.parseInt(inputWeight.getText().toString());
                burnBox = (EditText) findViewById(R.id.burnBox);
                repsMinutesBox = (EditText) findViewById(R.id.repBox);
                repsMinutesInt = Integer.parseInt(repsMinutesBox.getText().toString());
                total = (appropriateRatio * (float) (intWeight / 150) * (float) repsMinutesInt);
                burnBox.setText(total.toString());

            }
        });
        listView = (ListView)findViewById(R.id.mainListView);
        ArrayList newList = new ArrayList<>();
        mainAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newList);


        otherOptionsButton = (Button)findViewById(R.id.otherExercisesButton);
        otherOptionsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (String exercise : exerciseMap.keySet()) {
                    appropriateRatio = exerciseMap.get(exercise);
                    inputWeight = (EditText) findViewById((R.id.weightText));
                    intWeight = Integer.parseInt(inputWeight.getText().toString());
                    burnBox = (EditText) findViewById(R.id.burnBox);
                    burnVal = Float.parseFloat(burnBox.getText().toString());
                    newRepsMinutes = (150*burnVal / (appropriateRatio * (float)intWeight));
                    newRepsMinutesParsed = newRepsMinutes.toString();
                    mainAdapter.add(exercise + "\n" + newRepsMinutesParsed);
                }
            }

        });
        listView.setAdapter((mainAdapter));


    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

}

