package com.example.michael.gasfinder;

import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity {
    Location myLocation;
    GasAPI gasAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myLocation = new Location(LocationManager.GPS_PROVIDER);

        //TODO(TAY): Will need to get latitude/longitude from some input class
        myLocation.setLatitude(37.228260);
        myLocation.setLongitude(-80.428548);

        gasAPI = new GasAPI(this);
    }

    public void getLocations(Location l) {
        gasAPI.getNearbyStations(l, 100, "Unleaded", "Distance");
    }
}
