package rohksin.com.boundservicedemo2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Illuminati on 12/20/2017.
 */

public class LocationUtility {


    public static final String LOCATION_CHANGE = "rohksin.com.boundservicedemo2.LOCATION_CHANGE";
    public static final String CURRENT_LOCATION = "rohksin.com.boundservicedemo2.CURRENT_LOCATION";


    public static void checkLocationPermission(Context context)
    {
        if(!hasFineLocationPermission(context))
        {
            requestFineLocationPermission(context);
        }
    }



    private static boolean hasFineLocationPermission(Context context)
    {
        return ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private static void requestFineLocationPermission(Context context)
    {

          if(ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity)context,Manifest.permission.ACCESS_FINE_LOCATION))
          {
              Toast.makeText(context,"Please provide access",Toast.LENGTH_LONG).show();
          }
          else{

              ActivityCompat.requestPermissions((AppCompatActivity)context,
                      new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                      999
              );

          }
    }



}
