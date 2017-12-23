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
import android.util.Log;
import android.widget.Toast;

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


    public class MyLocationBinder extends Binder {

        public MyLocationService getService() {
            return MyLocationService.this;
        }
    }


    public void initLocationManager() {


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(MyLocationService.this,"Permission is not given",Toast.LENGTH_SHORT).show();

        }
        else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, LOCATION_UPDATE_DISTANCE, this);
        }


    }



    //*************************************************************
    // LOCATION MANAGER CALLBACKS
    //************************************************************


    @Override
    public void onLocationChanged(Location location) {

        Log.d(TAG, "Location Changed");
        Intent locationUpdate = new Intent();
        locationUpdate.setAction(LocationUtility.LOCATION_CHANGE);
        locationUpdate.putExtra(LocationUtility.CURRENT_LOCATION,getCurrentLoaction());
        sendBroadcast(locationUpdate);

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Log.d(TAG,"onStatusChanged");

    }

    @Override
    public void onProviderEnabled(String provider) {

        Log.d(TAG,"onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {

        Log.d(TAG,"onStatusDisabled");

    }

    //**************************************************************************
    // Service public API Methods
    //***************************************************************************



    public Location getCurrentLoaction() {

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(canGiveLoaction(isGPSEnabled,isNetworkEnabled))
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(MyLocationService.this,"Permission is not given",Toast.LENGTH_SHORT).show();
            }
            else
            {
                return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }

        return null;
    }



    private boolean canGiveLoaction(boolean isGPSEnabled, boolean isNetworkEnabled)
    {
        if(!isGPSEnabled && !isNetworkEnabled)
            return false;
        else return true;
    }





}
