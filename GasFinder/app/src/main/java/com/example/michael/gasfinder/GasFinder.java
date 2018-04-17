package com.example.michael.gasfinder;

import android.content.Intent;
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

        toHistory = (Button) findViewById(R.id.toHistory);
        toLogger = (Button) findViewById(R.id.toLogger);
        toList = (Button) findViewById(R.id.toList);
    }

    public void onClickList(View view)
    {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void onClickLog(View view)
    {
        Intent intent = new Intent(/*this, LogActivity.class*/);
        startActivity(intent);
    }

    public void onClickHistory(View view)
    {
        Intent intent = new Intent(/*this, HistoryActivity.class*/);
        startActivity(intent);
    }
}
