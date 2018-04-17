package com.example.michael.gasfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class DetailActivity extends AppCompatActivity
{
    private GoogleMap mMap;

    //TextViews
    TextView regularPriceTextView;
    TextView plusPriceTextView;
    TextView premiumPriceTextView;
    TextView dieselPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //TextViews
        regularPriceTextView = findViewById(R.id.regularPriceTextView);
        plusPriceTextView = findViewById(R.id.plusPriceTextView);
        premiumPriceTextView = findViewById(R.id.premiumPriceTextView);
        dieselPriceTextView = findViewById(R.id.dieselPriceTextView);
    }
}
