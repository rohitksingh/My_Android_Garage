package rohksin.com.googleplayserviceslocationapidemo;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Illuminati on 12/24/2017.
 */

public class GeoCodingService extends IntentService {


    private String TAG = "GeoAddress";

    public GeoCodingService() {
        super("GeoCodingService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        Location location = intent.getParcelableExtra(Constants.LOCATION_COORDINATES);

        List<Address> addresses = null;

        try {

            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);

        } catch (IOException e) {
            e.printStackTrace();
        }


        if(addresses==null || addresses.size()==0)
        {
            Log.d(TAG,"No addess found");
        }
        else
        {
            String firstLineAddress = addresses.get(0).getAddressLine(0);
            Log.d(TAG,firstLineAddress);

            Intent streetAddress = new Intent(Constants.RECEIVE_STREET_ADDRESS);
            streetAddress.putExtra(Constants.LOCATION_COORDINATES,firstLineAddress);
            sendBroadcast(streetAddress);
        }

    }
}
