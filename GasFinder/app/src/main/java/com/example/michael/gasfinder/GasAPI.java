package com.example.michael.gasfinder;

import android.location.Location;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Tay on 4/11/2018.
 * Class that directly links to the api for our gas site
 */

public class GasAPI implements Response.Listener<String>, Response.ErrorListener {

    private GasFinder gasFinder;
    private RequestQueue queue;

    private final String APIKEY = "83j74qb9so";
    private final String SITEDOMAIN = "http://api.mygasfeed.com";

    public GasAPI(GasFinder act) {
        gasFinder = act;
        queue = Volley.newRequestQueue(gasFinder);
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
        int x=2;// retry count
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("stations");
            gasFinder.saveStations(jsonArray);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
