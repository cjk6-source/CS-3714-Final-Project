package com.example.michael.gasfinder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GasFinder extends AppCompatActivity {

    Button toHistory;
    Button toLogger;
    Button toList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_finder);

        toHistory = findViewById(R.id.toHistory);
        toLogger = findViewById(R.id.toLogger);
        toList = findViewById(R.id.toList);
    }

    public void onClickList(View view)
    {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void onClickLog(View view)
    {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }

    public void onClickHistory(View view)
    {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            //gpsManager.register();
        }
    }
}
