package com.example.michael.gasfinder;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback
{
    private GoogleMap gMap;
    private ArrayList<GasStation> nearbyStations;
    private Location curUserLocation;
    LatLng curUserLatlng;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(curUserLocation!=null)
        {
            curUserLatlng = new LatLng(curUserLocation.getLatitude(),curUserLocation.getLongitude());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if(gMap!=null)
        {
            markStationLocations();
        }
    }

    public void markStationLocations()
    {
        if(nearbyStations.size()>0)
        {
            for(int i=0;i<nearbyStations.size();i++)
            {
                LatLng tempStation = new LatLng(nearbyStations.get(i).getLatitude(),nearbyStations.get(i).getLongitude());
                gMap.addMarker(new MarkerOptions().position(tempStation)
                        .title(nearbyStations.get(i).getStationName()));
            }
            gMap.moveCamera(CameraUpdateFactory.newLatLng(curUserLatlng));
        }
    }
}
