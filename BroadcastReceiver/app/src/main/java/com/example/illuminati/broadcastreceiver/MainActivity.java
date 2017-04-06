package com.example.illuminati.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_BROADCAST = "com.example.illuminati.broadcastreceiver.CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
           1)Create Instanse of BroadcastReceiver And IntentFilter
           2)Register your reciver
           3)Broadcst your message
           ALWAYS REGISTER YOUR RECEIVER THEN BROADCAT
           4) Thats it :)
         */

        Intent i = new Intent(CUSTOM_BROADCAST);

        BroadcastReceiver receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(CUSTOM_BROADCAST);
        registerReceiver(receiver,filter);

        sendBroadcast(i);

    }


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


    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            // Write Action that need to run when Recevies a Broadcast Message

            //Toast.makeText(context,"headset changed",Toast.LENGTH_SHORT).show();
            Log.d("Rohit","INSIDE RECEIVER");

        }
    }


}