package rohksin.com.gpslocationdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private static final String LOCATION_TAG = "locationTag";

    private TextView locationView;
    private Button getLoaction;

    private String currentLocation;


    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean canGetLocation;
    private Location location;
    private double longitude;
    private double latitude;
    private static int LOCATION_UPDATE_INTERVAL;
    private static int LOCATION_UPDATE_DISTANCE;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationView = (TextView) findViewById(R.id.location);
        getLoaction = (Button) findViewById(R.id.getLocation);

        getLoaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationView.setText(getLocation());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // return TODO;
            Log.d(LOCATION_TAG, "Runtime Permission");

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 188);

        }

    }


    public String getLocation() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            Log.d(LOCATION_TAG, "No network provider is available");
        } else {
            canGetLocation = true;

            if (isNetworkEnabled) {


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    //return TODO;
                }

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, LOCATION_UPDATE_DISTANCE, this);

                if(locationManager!=null)
                {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                     if(location!=null)
                     {
                         longitude = location.getLongitude();
                         latitude = location.getLatitude();
                     }
                }

            }


            if(isGPSEnabled)
            {
                if(location==null)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,LOCATION_UPDATE_INTERVAL,LOCATION_UPDATE_DISTANCE,this);

                      if(locationManager!=null)
                      {
                          location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                          if(location!=null)
                          {
                              latitude = location.getLatitude();
                              longitude = location.getLongitude();
                          }
                      }
                }
            }

        }



        return currentLocation = "Latitude :"+latitude+"\nLongitude :"+longitude;
    }




        @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 188: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
