package com.example.illuminati.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.illuminati.services.Services.MyService;

public class MainActivity extends AppCompatActivity {

    public static final String UPDATE_UI ="com.example.illuminati.services.UPDATE_UI";
    public static final String VALUE_I_NEED ="com.example.illuminati.services.VALUE_I_NEED";

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.value);

        registerReceiver();

        Intent i = new Intent(this, MyService.class);
        startService(i);
    }

    //********************************************************************************************
    // Private Methods
    //********************************************************************************************

    private void registerReceiver()
    {
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String value = intent.getStringExtra(VALUE_I_NEED);
                text.setText(value);

            }
        };

        IntentFilter filter = new IntentFilter(UPDATE_UI);
        registerReceiver(receiver,filter);
    }
}
