package com.example.michael.gasfinder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ListActivity extends AppCompatActivity {

    GasAPI gasAPI;
    GPSBinder binder;

    private final String INITIALIZE_STATUS = "initialization_status";

    boolean isBound;
    boolean isInitialized;
    Location currentLocation;

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        gasAPI = new GasAPI(this);
        binder = new GPSBinder(this);

        if (savedInstanceState != null) {
            isInitialized = savedInstanceState.getBoolean(INITIALIZE_STATUS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            isBound = binder.bindGPSService();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isBound) {
            binder.unBindGPSService();
            isBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isInitialized && !isBound) {
            binder.bindGPSService();
        }
    }

    public void findStations(View view) {
        if (isBound) {
            currentLocation = binder.getSystemLocation();
            Log.d("yee", currentLocation.toString());
        }
    }

    public void getLocations(Location l) {
        gasAPI.getNearbyStations(l, 100, "Unleaded", "Distance");
    }
}
