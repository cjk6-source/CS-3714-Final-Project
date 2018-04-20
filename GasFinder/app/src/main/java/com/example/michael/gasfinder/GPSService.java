package com.example.michael.gasfinder;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

public class GPSService extends Service implements LocationListener{

    private Context myContext;

    private LocationManager locationManager;
    private String LOCATIONPROVIDER = LocationManager.GPS_PROVIDER;
    private Location currentLocation;

    public GPSService() {
        //Default Constructor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myContext = getApplicationContext();
        locationManager = (LocationManager) myContext.getSystemService(Context.LOCATION_SERVICE);
    }

    public void register() {
        if (ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LOCATIONPROVIDER, 5000, 5, this);
            currentLocation = locationManager.getLastKnownLocation(LOCATIONPROVIDER);
        }
    }

    public void unregister() {
        if (ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(this);
        }

    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public class MyBinder extends Binder
    {
        GPSService getService()
        {
            return GPSService.this;
        }
    }

    private final IBinder iBinder = new MyBinder();

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        //gpsBinder.updateCurrentlocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
