package com.example.michael.gasfinder;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.Locale;

public class GPSManager implements LocationListener{
    DetailsScreen detailsScreen;
    String LOCATIONPROVIDER = LocationManager.GPS_PROVIDER;
    LocationManager locationManager;

    public GPSManager(DetailsScreen dS) {
        this.detailsScreen = dS;
        locationManager = (LocationManager)detailsScreen.getSystemService(Context.LOCATION_SERVICE);
    }

    public void register()
    {
        if (ActivityCompat.checkSelfPermission(detailsScreen, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(detailsScreen, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LOCATIONPROVIDER, 5000, 5,this);
            currentLocation = locationManager.getLastKnownLocation(LOCATIONPROVIDER);
            detailsScreen.updateCurrentLocation(/*currentLocation*/);
        }
    }

    public void unregister()
    {
        if (ActivityCompat.checkSelfPermission(detailsScreen, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(detailsScreen, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationManager.removeUpdates(this);
        }
    }

    Location currentLocation;

    public Location getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.currentLocation = location;
        detailsScreen.updateCurrentLocation(/*currentLocation*/);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
