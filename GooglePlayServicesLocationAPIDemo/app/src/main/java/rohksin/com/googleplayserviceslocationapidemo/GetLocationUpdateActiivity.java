package rohksin.com.googleplayserviceslocationapidemo;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 12/24/2017.
 */

public class GetLocationUpdateActiivity extends AppCompatActivity {

    private TextView currentLocation;

    private LocationRequest locationRequest;
    private List<LocationRequest> locationRequestList;
    private boolean requestingLocationUpdates = false;

    private FusedLocationProviderClient locationProviderClient;
    private LocationCallback locationCallback;

    private String TAG = "UPDATES";

    //******************************************************************************************
    // Activity callback Methods
    //******************************************************************************************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        currentLocation = (TextView) findViewById(R.id.location);

        setLocationCallback();
        createLocationRequest();
        checkWithSystemSetting();

    }


    @Override
    public void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            requstLocationUpdates();
        }

    }

    /*
    @Override
    public void onPause()
    {
        super.onPause();
        stopLocationUpdate();
    }

    public void stopLocationUpdate()
    {
        locationProviderClient.removeLocationUpdates(locationCallback);
    }

    */


    //******************************************************************************************
    // Helper mrthods
    //******************************************************************************************


    //     Setting up locationCallBack

    private void setLocationCallback()
    {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {

                for (Location location : locationResult.getLocations()) {

                    Log.d(TAG,"Current loc "+location.toString());
                }
            }

        };
    }

    //     Location Request to tell Location provider when and what kind of accuracy is requested

    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(2000);                                       // prefer to get location update in every 2 secs
        locationRequest.setFastestInterval(2000);                                // can handle at updates at the max rate of 2 sec interval
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);     // High accuracy
        locationRequestList = new ArrayList<LocationRequest>();
        locationRequestList.add(locationRequest);
    }

    // Starting location updates

    private void requstLocationUpdates() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           Log.d(TAG,"Permission not given");
        }
        locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }


    // Check whether System location setting satisfies the location request/s

    private void checkWithSystemSetting()
    {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addAllLocationRequests(locationRequestList);

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(builder.build())
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                        Log.d(TAG,"Success");
                        requestingLocationUpdates = true;
                        requstLocationUpdates();

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d(TAG,"Failure");

                        if(e instanceof ResolvableApiException)
                        {
                            ResolvableApiException resolvableApiException = (ResolvableApiException)e;

                            try {
                                resolvableApiException.startResolutionForResult(GetLocationUpdateActiivity.this,99);
                            } catch (IntentSender.SendIntentException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }
                });
    }

}