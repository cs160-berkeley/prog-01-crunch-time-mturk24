package com.example.matthewturk.wearrepresent;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by matthewturk on 3/3/16.
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String string1 = "/55555";
    private static final String string2 = "/92740";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("Test", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)
//        Intent intent = new Intent(WatchListenerService.this, FirstSecondView.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            //you need to add this flag since you're starting a new activity from a service
//        intent.putExtra("zipCode", "55555");
////        Log.d("T", "about to start watch MainActivity with zipCode: Fred");
//        startActivity(intent);


        if( messageEvent.getPath().equalsIgnoreCase( string1 ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(WatchListenerService.this, FirstSecondView.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("zipCode", "55555");
            Log.d("T", "about to start watch MainActivity with CAT_NAME: Fred");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( string2 )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(WatchListenerService.this, FirstSecondView.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("zipCode", "92740");
            Log.d("T", "about to start watch MainActivity with CAT_NAME: Lexy");
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
