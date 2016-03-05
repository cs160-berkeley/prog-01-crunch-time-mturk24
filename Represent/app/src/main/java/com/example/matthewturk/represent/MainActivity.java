package com.example.matthewturk.represent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;
    String string1;
    String string2;
    String string3;
    String zip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        addListenerOnButton1();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        final TextView myText1 = (TextView)findViewById(R.id.textView4);
//        string1 = (String) myText1.getText();
//        final TextView myText2 = (TextView)findViewById(R.id.textView7);
//        string2 = (String) myText2.getText();
//        final TextView myText3 = (TextView)findViewById(R.id.textView8);
//        string3 = (String) myText3.getText();
        final EditText myText = (EditText)findViewById(R.id.editText);
        zip = myText.getText().toString();
    }
    public void getOtherView(View v) {
        Intent intent = new Intent(getApplicationContext(), SecondClass.class);
        startActivity(intent);
    }

    public void addListenerOnButton() {

        final EditText myInput = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myInput.getText().length() == 5) {
                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    sendIntent.putExtra("zipCode", zip);
                    startService(sendIntent);
                    Intent intent = new Intent(getApplicationContext(), SecondClass.class);
                    startActivity(intent);

                }
            }
        });
    }
//
//                watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                watchIntent.putExtra("CANDIDATE_NAME", "John Doe");
//                watchIntent.putExtra("PARTY_NAME", "Democrat");
//                startService(watchIntent);
//
//                zipCodeEntry = zipCode.getText().toString();
//                phoneIntent = new Intent(phoneHomeScreen.this,phoneCongressionalCandidates.class);
//                phoneIntent.putExtra("zipCodeEntry", zipCodeEntry);
//                startActivity(phoneIntent);
//
    public void addListenerOnButton1() {

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("zipCode", "55555");
                startService(sendIntent);
                Intent intent = new Intent(getApplicationContext(), SecondClass.class);
                startActivity(intent);

            }
        });
    }


//    ArrayAdapter<String> adapter;
//    EditText editText;
//    ArrayList<String> itemList;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        String[] items={"Apple","Banana","Clementine"};
//        itemList=new ArrayList<String>(Arrays.asList(items));
//        adapter=new ArrayAdapter<String>(this,R.layout.list_layout,R.id.txtview,itemList);
//        ListView listV=(ListView)findViewById(R.id.list);
//        listV.setAdapter(adapter);
//        editText=(EditText)findViewById(R.id.txtInput);
//        Button btAdd=(Button)findViewById(R.id.btAdd);
//        btAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newItem=editText.getText().toString();
//                // add new item to arraylist
//                itemList.add(newItem);
//                // notify listview of data changed
//                adapter.notifyDataSetChanged();
//            }
//
//        });
//
//    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
