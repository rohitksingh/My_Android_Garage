package com.rohitksingh.googlemarkerdemo;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveListener, OnSuccessListener<Location> {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private Marker marker;
    private MarkerOptions markerOptions;
    private CameraPosition cameraPosition;
    private LatLng initial_location = new LatLng(25.393860, 81.855170);
    private LatLng currentLatLng;

    private static final String TAG = "MapsActivity";
    
    private TextView currentLocation;
    private ImageView dummyDot;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dummyDot = findViewById(R.id.dummyDot);
        currentLocation = findViewById(R.id.address);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnCameraIdleListener(this);
        mMap.setOnCameraMoveListener(this);
        mMap.setOnCameraMoveStartedListener(this);
        getCurrentLocation();
        markerOptions = new MarkerOptions();
        markerOptions.draggable(true);

    }

    @Override
    public void onCameraIdle() {

        addMarker();
        try {
            getAddress(mMap.getCameraPosition().target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onCameraMoveStarted(int i) {

        if(marker!=null) {

            marker.remove();

            if(dummyDot.getVisibility()==View.GONE){
                dummyDot.setVisibility(View.VISIBLE);
            }
        }
    }


    /***********************************************************************************************
     *
     *                          Private Helper method
     *
     ***********************************************************************************************/

    private void addMarker(){

        LatLng latLng = mMap.getCameraPosition().target;
        markerOptions.position(latLng);
        marker = mMap.addMarker(markerOptions);

        if(dummyDot.getVisibility()!= View.GONE){
            dummyDot.setVisibility(View.GONE);
        }

    }

    private void getAddress(LatLng latLng) throws IOException {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();

        Log.d(TAG, "getAddress: "+addresses);
        currentLocation.setText(address+", "+city+", "+state+", "+country);
    }


    private void getCurrentLocation(){

        fusedLocationClient.getLastLocation().addOnSuccessListener(this);

    }

    // Success listener when the last loaction is received this method is triggered.
    @Override
    public void onSuccess(Location location) {

        if (location != null) {
             try {
                    currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                    markerOptions.position(currentLatLng).title("Deliver Here");
                    marker = mMap.addMarker(markerOptions);
                    cameraPosition = new CameraPosition.Builder()
                                        .target(currentLatLng)
                                        .zoom(15)
                                        .bearing(90)
                                        .tilt(90)
                                        .build();

                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    getAddress(initial_location);

             } catch (IOException e) {
                                e.printStackTrace();
             }
        }

    }
}
