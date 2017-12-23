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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TimerService timerService;
    private Intent timerIntent;
    private boolean timerBound = false;


    private MyLocationService locationService;
    private Intent locationIntent;
    private boolean locationBound = false;

    private TextView textView;
    private String TAG ="LOC";
    private Location currentLocation;
    private Button openInMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.timerValue);
        openInMap = (Button)findViewById(R.id.openInMap);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"Service null "+(locationService==null));

                textView.setText(locationService.getCurrentLoaction().toString());
                updateCurrentLocation(locationService.getCurrentLoaction());
            }
        });

        openInMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLocation!=null)
                {
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", currentLocation.getLatitude(), currentLocation.getLongitude());
                    Intent openMap = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(openMap);
                }
            }
        });


        registerReceiver(new ServiceReceivers(),new IntentFilter("TIMER"));
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


    public class ServiceReceivers extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG,"Intent received");

            if(intent.getAction().equals(LocationUtility.LOCATION_CHANGE))
            {

                Location location = (Location) intent.getSerializableExtra(LocationUtility.CURRENT_LOCATION);
                Toast.makeText(MainActivity.this,"New Location",Toast.LENGTH_SHORT).toString();
                textView.setText(location.getLatitude()+"\n"+location.getLongitude());
                updateCurrentLocation(location);
            }


            if(intent.getAction().equals("TIMER"))
            {
                textView.setText(intent.getStringExtra("TIMERVALUE"));
            }
        }
    }


    public void updateCurrentLocation(Location location)
    {
        currentLocation = location;
    }






}
