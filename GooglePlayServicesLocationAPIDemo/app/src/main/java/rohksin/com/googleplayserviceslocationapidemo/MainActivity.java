package rohksin.com.googleplayserviceslocationapidemo;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    //private GoogleApiClient apiClient;
    private Location currentLocation;
    private FusedLocationProviderClient locationProviderClient;
    private TextView locationView;
    private TextView streetAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationView = (TextView)findViewById(R.id.location);
        streetAddress = (TextView)findViewById(R.id.streetAddress);

        registerReceiver(new GeoAddressReceiver(),new IntentFilter(Constants.RECEIVE_STREET_ADDRESS));

        locationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
            }
        });


    }

    public void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Permisson not given", Toast.LENGTH_SHORT).show();
        }
        else {
            locationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {

                        @Override
                        public void onSuccess(Location location) {

                            currentLocation = location;
                            Log.d("LOcation",currentLocation.toString());
                            locationView.setText(currentLocation.toString());

                            getStreetAddress(currentLocation);
                        }
                    });

        }

    }




   ///           Calling Geo coding service to get Address fom location coordinates

    public void getStreetAddress(Location location)
    {
        Intent getAddressIntent = new Intent(MainActivity.this, GeoCodingService.class);
        getAddressIntent.putExtra(Constants.LOCATION_COORDINATES,location);
        startService(getAddressIntent);
    }


    private class GeoAddressReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent)
        {
            String geoCodedAddress = intent.getStringExtra(Constants.LOCATION_COORDINATES);
            streetAddress.setText(geoCodedAddress);
        }
    }


}
