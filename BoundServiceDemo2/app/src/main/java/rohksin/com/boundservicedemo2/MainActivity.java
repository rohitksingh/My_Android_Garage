package rohksin.com.boundservicedemo2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

import rohksin.com.boundservicedemo2.Services.MyLocationService;
import rohksin.com.boundservicedemo2.Services.TimerService;
import rohksin.com.boundservicedemo2.Utility.LocationUtility;

public class MainActivity extends AppCompatActivity {


    // Timer Service and related
    private TimerService timerService;
    private Intent timerIntent;
    private boolean timerBound = false;


    // Location Service and related
    private MyLocationService locationService;
    private Intent locationIntent;
    private boolean locationBound = false;


    private String TAG ="LOC";
    private TextView locationFromGps;
    private TextView locationFromNetwork;
    private TextView locationFromPlayService;

    private Location currentLocation;


    //********************************************************************************************
    //  Activity Callback methods
    //********************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationFromGps = (TextView)findViewById(R.id.locationFromGps);
        locationFromNetwork = (TextView)findViewById(R.id.locationFromNetwork);
        locationFromPlayService = (TextView)findViewById(R.id.locationFromPlayService);

        registerReceiver(new MyLocationReceiver(),new IntentFilter(LocationUtility.LOCATION_CHANGE));
    }


    @Override
    public void onStart()
    {
        super.onStart();
        if(timerIntent==null)
        {
            timerIntent = new Intent(this,TimerService.class);
            bindService(timerIntent, timerserviceConnection, Context.BIND_AUTO_CREATE);
            startService(timerIntent);
        }

        if(locationIntent==null)
        {
            locationIntent = new Intent(this,MyLocationService.class);
            bindService(locationIntent,locationServiceConnection,Context.BIND_AUTO_CREATE);
            startService(locationIntent);
        }
    }

    public void onDestroy()
    {
        stopService(timerIntent);
        timerService = null;
        stopService(locationIntent);
        locationService = null;
        super.onDestroy();
    }

    //********************************************************************************************
    //  Service Connectors : Provides access to service public API
    //********************************************************************************************

    private ServiceConnection timerserviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            TimerService.TimerBinder binder = (TimerService.TimerBinder)service;
            timerService = binder.getService();
            timerBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            timerBound = false;
        }
    };


    private ServiceConnection locationServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocationService.MyLocationBinder binder = (MyLocationService.MyLocationBinder)service;
            locationService = binder.getService();
            locationBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            locationBound = false;
        }
    };


    //********************************************************************************************
    //  BroadCast Receiver : To listen loaction change updates from service
    //********************************************************************************************

    public class MyLocationReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(LocationUtility.LOCATION_CHANGE))
            {

                String fromGps =  intent.getStringExtra(LocationUtility.CURRENT_LOCATION_FROM_GSP);
                String fromNetwork = intent.getStringExtra(LocationUtility.CURRENT_LOCATION_FROM_NETWORK);

                if(locationFromGps!=null)
                locationFromGps.setText("GPS: "+fromGps);
                if(locationFromNetwork!=null)
                locationFromNetwork.setText("NET: "+fromNetwork);

            }

        }
    }

    class TimerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals("TIMER"))
            {
                // Handle Timer value
            }
        }
    }


    public void openLocationWithGoogleMap()
    {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", currentLocation.getLatitude(), currentLocation.getLongitude());
        Intent openMap = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(openMap);
    }


}
