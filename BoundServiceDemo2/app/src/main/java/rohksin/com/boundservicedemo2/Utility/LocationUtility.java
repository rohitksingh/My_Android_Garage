package rohksin.com.boundservicedemo2.Utility;

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
    public static final String CURRENT_LOCATION_FROM_GSP = "rohksin.com.boundservicedemo2.GPS";
    public static final String CURRENT_LOCATION_FROM_NETWORK = "rohksin.com.boundservicedemo2.NETWORK";

}
