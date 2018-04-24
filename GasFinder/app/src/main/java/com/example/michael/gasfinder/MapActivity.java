package com.example.michael.gasfinder;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener
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

        Intent intent = this.getIntent();

        Bundle bundle = intent.getExtras();

        nearbyStations = (ArrayList<GasStation>) bundle.getSerializable(GasFinder.NEARBY_STATIONS);
        curUserLatlng = new LatLng(
                intent.getDoubleExtra(GasFinder.CURRENT_LATITUDE, 0),
                intent.getDoubleExtra(GasFinder.CURRENT_LONGITUDE, 0));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setOnMarkerClickListener(this);
        if(gMap!=null)
        {
            markStationLocations();
        }
    }

    public void markStationLocations()
    {
        if(nearbyStations != null)
        {
            for(int i=0;i<nearbyStations.size();i++)
            {
                LatLng tempStation = new LatLng(nearbyStations.get(i).getLatitude(),nearbyStations.get(i).getLongitude());
                gMap.addMarker(new MarkerOptions().position(tempStation)
                        .title(nearbyStations.get(i).getStationName()));
            }
            float zoomLevel = 16.0f;
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curUserLatlng,zoomLevel));
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Log.d("Marker position lat: ", String.valueOf(marker.getPosition().latitude));
        Log.d("Marker position long: ", String.valueOf(marker.getPosition().longitude));
        if(nearbyStations !=  null)
        {
            for (int i = 0; i < nearbyStations.size(); i++)
            {
                if (nearbyStations.get(i).getLatitude() == marker.getPosition().latitude && nearbyStations.get(i).getLongitude() == marker.getPosition().longitude)
                {
                    //Log.d("Station Name: ", nearbyStations.get(i).getStationName());
                    Intent intent = new Intent(this, DetailsScreen.class);
                    intent.putExtra("GAS STATION OBJECT", nearbyStations.get(i));
                    startActivity(intent);
                }
            }
        }
        return true;
    }
}
