package com.example.michael.gasfinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;

/**
 * Created by Tay on 4/11/2018.
 */

public class GPSBinder {

    GPSService service;
    Context myServiceContext;
    Intent startGPSService;

    boolean isBound = false;

    public GPSBinder(Context context) {
        myServiceContext = context;
        startGPSService = new Intent(context, GPSService.class);
    }

    public boolean bindGPSService() {
        myServiceContext.bindService(startGPSService, gpsServiceConnection, Context.BIND_AUTO_CREATE);

        if (service != null) {
            service.register();
            isBound = true;
            GasFinder finder = (GasFinder) myServiceContext;
            finder.findStations();
            return true;
        }
        return isBound;
    }

    public boolean unBindGPSService() {
        if (service != null) {
            myServiceContext.unbindService(gpsServiceConnection);
            service.unregister();
            isBound = false;
            return false;
        }
        return isBound;
    }

    public boolean isBound() {
        return isBound;
    }

    public Location getSystemLocation() {
        return service.getCurrentLocation();
    }

    public LocationManager getLocationManager() {
        return (LocationManager) myServiceContext.getSystemService(Context.LOCATION_SERVICE);
    }

    private ServiceConnection gpsServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GPSService.MyBinder binder = (GPSService.MyBinder) iBinder;
            service = binder.getService();
            isBound = bindGPSService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = unBindGPSService();
            service = null;
        }
    };

    public Location updateCurrentlocation(Location currentLocation) {
        return currentLocation;
    }

    public Context getContext() {
        return myServiceContext;
    }
}
