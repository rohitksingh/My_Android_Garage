package rohksin.com.boundservicedemo2.Services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import rohksin.com.boundservicedemo2.Utility.LocationUtility;

/**
 * Created by Illuminati on 12/20/2017.
 */

public class MyLocationService extends Service implements LocationListener {


    private LocationManager locationManager;
    private IBinder binder = new MyLocationBinder();


    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;

    private int LOCATION_UPDATE_INTERVAL = 1000; // inMills
    private int LOCATION_UPDATE_DISTANCE = 2;    // inMeters

    private String TAG = "location";


    //****************************************************************************************
    //   Service Lifecycle Methods
    //****************************************************************************************


    @Override
    public void onCreate() {
        initLocationManager();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {

        locationManager = null;
        return false;
    }

    //************************************************************************************************
    //   Binder : Binds service with other componets, provide access to Public API methods of service
    //************************************************************************************************


    public class MyLocationBinder extends Binder {

        public MyLocationService getService() {
            return MyLocationService.this;
        }
    }



    //*************************************************************
    // LOCATION MANAGER CALLBACKS
    //************************************************************

    @Override
    public void onLocationChanged(Location location) {

        Log.d(TAG, "Location Changed");
        Toast.makeText(MyLocationService.this, "Update", Toast.LENGTH_SHORT).show();

        Location locationFromGps = getLocationFromGps();
        Location loctionFromNetwork = getLocationFromNetwork();
        String fromGps = locationFromGps.getLatitude()+", "+locationFromGps.getLongitude();
        String fromNetwork = loctionFromNetwork.getLatitude()+", "+loctionFromNetwork.getLongitude();


        Intent locationUpdates = new Intent();
        locationUpdates.setAction(LocationUtility.LOCATION_CHANGE);

        locationUpdates.putExtra(LocationUtility.CURRENT_LOCATION_FROM_GSP, fromGps);
        locationUpdates.putExtra(LocationUtility.CURRENT_LOCATION_FROM_NETWORK,fromNetwork);
        sendBroadcast(locationUpdates);


    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Log.d(TAG, "onStatusChanged");

    }

    @Override
    public void onProviderEnabled(String provider) {

        Log.d(TAG, "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {

        Log.d(TAG, "onStatusDisabled");

    }



    //****************************************************************************************
    //   Service private Helper methos, Used only by Service
    //****************************************************************************************


    private void initLocationManager() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(MyLocationService.this, "Permission is not given", Toast.LENGTH_SHORT).show();

        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, LOCATION_UPDATE_DISTANCE, this);
        }

    }


    private boolean canGiveLoaction(boolean isGPSEnabled, boolean isNetworkEnabled)
    {
        if(!isGPSEnabled && !isNetworkEnabled)
            return false;
        else return true;
    }



    //**************************************************************************
    // Service public API Methods
    //***************************************************************************


    public Location getLocationFromGps() {

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (isGPSEnabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(MyLocationService.this, "Permission is not given", Toast.LENGTH_SHORT).show();
            } else {
                return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }

        return null;
    }


    public Location getLocationFromNetwork() {
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isNetworkEnabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MyLocationService.this, "Permission is not granted", Toast.LENGTH_SHORT).toString();
            }
            return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        return null;

    }



}
