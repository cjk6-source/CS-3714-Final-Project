package com.example.michael.gasfinder;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailsScreen extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GPSManager gpsManager;
    Button recenter;
    TextView station_name;
    TextView station_address;
    TextView regular;
    TextView mid;
    TextView premium;
    TextView diesel;
    double lat;
    double lon;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        gpsManager = new GPSManager(this);

        recenter = (Button) findViewById(R.id.recenter);
        station_name = (TextView) findViewById(R.id.station_name);
        station_address = (TextView) findViewById(R.id.station_address);
        regular = (TextView) findViewById(R.id.regular);
        mid = (TextView) findViewById(R.id.mid);
        premium = (TextView) findViewById(R.id.premium);
        diesel = (TextView) findViewById(R.id.diesel);

        Intent intent = getIntent();
        //station_name.setText(intent.getStringExtra(STATION_NAME));
        //station_address.setText(intent.getStringExtra(STATION_ADDRESS));
        //regular.setText(intent.getStringExtra(REGULAR));
        //mid.setText(intent.getStringExtra(MID));
        //premium.setText(intent.getStringExtra(PREMIUM));
        //diesel.setText(intent.getStringExtra(DIESEL));
        //lat = intent.getDoubleExtra("LATITUDE", 0);
        //lon = intent.getDoubleExtra("LONGITUDE", 0);
        latLng = new LatLng(lat, lon);
        //updateCurrentLocation();
    }

    public void onRecenter(View view)
    {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    public void onLog(View view)
    {
        Intent intent = new Intent(/*this, LogActivity.class*/);
        startActivity(intent);
    }

    public void onBackToList(View view)
    {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gpsManager.unregister();
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void updateCurrentLocation()
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}
