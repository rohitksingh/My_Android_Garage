package rohksin.com.intentservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Illuminati on 11/4/2017.
 */

public class ThirdActivity extends AppCompatActivity {


    private TextView currentTimer;
    private Button boundServiceButton;


    private DemoBoundService demoBoundService;
    private boolean isServiceBound;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        currentTimer = (TextView)findViewById(R.id.currntTimer);
        boundServiceButton = (Button)findViewById(R.id.button);
        boundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isServiceBound)
                {
                   int value = demoBoundService.getCurrentTimerValue();
                    currentTimer.setText(value+"");
                }
        }
        });

    }


    @Override
    public void onStart()
    {
        super.onStart();
        Intent intent = new Intent(ThirdActivity.this,DemoBoundService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onStop()
    {
       super.onStop();
        if(isServiceBound)
        {
            unbindService(connection);
            isServiceBound = false;
        }
    }

    @Override
    public void onResume()
    {
       super.onResume();
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DemoBoundService.MyBinder binder = (DemoBoundService.MyBinder)service;
            demoBoundService = binder.getService();
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;
        }
    };

}