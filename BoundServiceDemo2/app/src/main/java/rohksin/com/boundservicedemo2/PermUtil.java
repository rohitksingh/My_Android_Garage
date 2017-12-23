package rohksin.com.boundservicedemo2;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.security.Permission;

/**
 * Created by Illuminati on 12/20/2017.
 */

public class PermUtil {


    public static boolean hasFineLocationPermission(Context context)
    {
        return ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestFineLocationPermission(Context context)
    {

        if(ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity)context,Manifest.permission.ACCESS_FINE_LOCATION))
        {
            Toast.makeText(context,"Please provide access",Toast.LENGTH_LONG).show();
        }
        else{

           ActivityCompat.requestPermissions((AppCompatActivity)context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 188);
        // ActivityCompat.requestPermissions((AppCompatActivity)context, new String[][]{ACCESS_FINE_LOCATION}, "sss");
        }
    }


}
