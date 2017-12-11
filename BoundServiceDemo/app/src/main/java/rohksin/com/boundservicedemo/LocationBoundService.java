package rohksin.com.boundservicedemo;

import android.Manifest;
import android.app.Activity;
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
import android.support.v7.widget.AppCompatButton;
import android.util.Log;

/**
 * Created by Illuminati on 11/4/2017.
 */

public class LocationBoundService extends Service{

    private String currentLoaction;
    private IBinder locationBinder = new LocationBinder();

    private Location location;
    private double longitude;
    private double latitude;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean canGetLocation;
    private static int  LOCATION_UPDATE_INTERVAL;
    private static int LOCATION_UPDATE_DISTANCE;

    private LocationManager locationManager;
    private LocationListener locationListener;


    //************************************************************************
    // Service Callbaclk methods
    //************************************************************************


    @Override
    public void onCreate() {


        locationManager  = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
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
        };
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return locationBinder;
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //************************************************************************
    // Private Methods
    //************************************************************************

    public String getCurrentLoaction() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {

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

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, LOCATION_UPDATE_DISTANCE, locationListener);

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
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,LOCATION_UPDATE_INTERVAL,LOCATION_UPDATE_DISTANCE,locationListener);

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

        currentLoaction = "Latitude :"+latitude+"\nLongitude :"+longitude;

        return currentLoaction;
    }


    public class LocationBinder extends Binder{

        public LocationBoundService getService()
        {
            return  LocationBoundService.this;
        }

    }

}
