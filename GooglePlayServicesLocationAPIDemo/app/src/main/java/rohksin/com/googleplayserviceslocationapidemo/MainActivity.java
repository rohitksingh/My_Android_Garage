package rohksin.com.googleplayserviceslocationapidemo;

import android.Manifest;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationView = (TextView)findViewById(R.id.location);

        locationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLoaction();
            }
        });


    }

    public void getCurrentLoaction() {
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
                        }
                    });

        }

    }

}
