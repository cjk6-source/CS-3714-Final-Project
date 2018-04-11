package com.example.michael.gasfinder;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class GPSService extends Service implements LocationListener{
    GPSBinder gpsBinder;

    LocationManager locationManager;


    public GPSService(GPSBinder gpsBinder) {
        this.gpsBinder = gpsBinder;
        locationManager = (LocationManager) gpsBinder.getSystemLocation();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onLocationChanged(Location location) {

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

    public class MyBinder extends Binder
    {
        GPSService getService()
        {
            return GPSService.this;
        }
    }

    private final IBinder iBinder = new MyBinder();


}
