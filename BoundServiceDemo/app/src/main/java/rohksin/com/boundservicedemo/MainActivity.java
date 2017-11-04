package rohksin.com.boundservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button getLocation;
    private Button startLocationUpdate;
    private TextView location;

    private LocationBoundService locationBoundService;
    private boolean isServiceBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = (TextView)findViewById(R.id.location);
        getLocation = (Button)findViewById(R.id.getLoaction);
        startLocationUpdate = (Button)findViewById(R.id.startService);

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(locationBoundService==null)
                {
                    location.setText("Service is inactive");
                }
                else {
                    String currentLoaction = locationBoundService.getCurrentLoaction();
                    location.setText(currentLoaction);
                }
            }
        });


        startLocationUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationBoundService.class);
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
            }
        });




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

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            LocationBoundService.LocationBinder binder = (LocationBoundService.LocationBinder)service;
            locationBoundService = binder.getService();
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
               isServiceBound = false;
        }
    };



}
