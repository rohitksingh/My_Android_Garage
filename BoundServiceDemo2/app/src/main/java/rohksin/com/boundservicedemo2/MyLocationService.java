package rohksin.com.boundservicedemo2;

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
import android.support.v4.app.LoaderManager;

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


    @Override
    public void onCreate() {

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {

    }


    public class MyLocationBinder extends Binder {

        public MyLocationService getService() {
            return MyLocationService.this;
        }
    }


    public void initLocationManager() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }


    //*************************************************************
    // LOCATION MANAGER CALLBACKS
    //************************************************************


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    //**************************************************************************
    // Service public API Methods
    //***************************************************************************

    public String getCurrentLoaction() {
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, LOCATION_UPDATE_DISTANCE, this);


    private boolean canGiveLoaction(boolean isGPSEnabled, boolean isNetworkEnabled)
    {
        if(!isGPSEnabled && !isNetworkEnabled)
            return false;
        else return true;
    }


}
