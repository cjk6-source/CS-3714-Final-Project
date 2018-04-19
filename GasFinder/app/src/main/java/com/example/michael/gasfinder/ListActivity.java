package com.example.michael.gasfinder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    private Location myLocation;
    private GasAPI gasAPI;
    private GPSBinder binder;
    private HashMap<Integer, GasStation> nearbyStations;

    private final String INITIALIZE_STATUS = "initialization_status";

    boolean isBound;
    boolean isInitialized;
    private Location currentLocation;

    private Spinner fuelSpinner, orderSpinner;
    private TextView radius;

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
        fuelSpinner = findViewById(R.id.fuelSpinner);
        orderSpinner = findViewById(R.id.orderSpinner);
        radius = findViewById(R.id.radius);

        String[] fuelTypes = new String[]{"reg", "mid", "pre", "diesel"};
        ArrayAdapter fuelAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, fuelTypes
        );
        fuelSpinner.setAdapter(fuelAdapter);

        String[] orderTypes = new String[]{"Distance", "Price"};
        ArrayAdapter orderAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, orderTypes
        );
        orderSpinner.setAdapter(orderAdapter);

        if (savedInstanceState != null) {
            isInitialized = savedInstanceState.getBoolean(INITIALIZE_STATUS);
        }

        nearbyStations = new HashMap<>();
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
            int distance = Integer.parseInt(radius.getText().toString());
            String fuelType = fuelSpinner.getSelectedItem().toString();
            String sortType = orderSpinner.getSelectedItem().toString();
            gasAPI.getNearbyStations(currentLocation, distance, fuelType, sortType);
        }
    }

    public void displayStations(JSONArray arr) {
        for (int i = 0; i < arr.length(); i++) {
            try {
                JSONObject o = arr.getJSONObject(i);
                GasStation station = new GasStation(o);
                nearbyStations.put(station.getId(), station);
                Log.d("Station", station.getDistance() + " " + station.getStationName() + " " + station.getReg_date());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private LinearLayout addRow(
            String name, String distance, String fuelType, String price, String address) {
        return null;
    }
}
