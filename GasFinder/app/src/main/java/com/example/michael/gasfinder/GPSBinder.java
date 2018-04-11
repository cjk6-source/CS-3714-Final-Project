package com.example.michael.gasfinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by Tay on 4/11/2018.
 */

public class GPSBinder {

    GPSService service;
    Context myServiceContext;
    boolean isBound;

    public void BindGPSService(Context context) {
        myServiceContext = context;
    }

    public Object getSystemLocation() {
        return myServiceContext.getSystemService(Context.LOCATION_SERVICE);
    }

    private ServiceConnection gpsServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GPSService.MyBinder binder = (GPSService.MyBinder) iBinder;
            service = binder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
            isBound = false;
        }
    };

    public void getGPSLocation() {
        if (isBound) {

        }
    }
}
