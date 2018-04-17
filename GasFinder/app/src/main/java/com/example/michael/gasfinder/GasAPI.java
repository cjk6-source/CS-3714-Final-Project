package com.example.michael.gasfinder;

import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by Tay on 4/11/2018.
 * Class that directly links to the api for our gas site
 */

public class GasAPI implements Response.Listener<String>, Response.ErrorListener {

    private ListActivity listActivity;
    private RequestQueue queue;

    private final String APIKEY = "83j74qb9so";
    private final String SITEDOMAIN = "http://api.mygasfeed.com";

    public GasAPI(ListActivity act) {
        listActivity = act;
        queue = Volley.newRequestQueue(listActivity);
    }

    public void getNearbyStations(Location location, int distance, String fuelType, String sortType) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        String mylat = latitude.toString();
        String mylong = longitude.toString();
        String myDistance = Integer.toString(distance);


        String formatted;
        //stations/radius/(Latitude)/(Longitude)/(distance)/(fuel type)/(sort by)/apikey.json
        formatted = String.format("%s/stations/radius/%s/%s/%s/%s/%s/%s.json",
                SITEDOMAIN, mylat, mylong, myDistance, fuelType, sortType, APIKEY);

        StringRequest request =
                new StringRequest(Request.Method.GET, formatted, this, this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Log.d("yee", response);
    }
}
