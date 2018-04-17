package com.example.michael.gasfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener
{

    //Buttons
    Button goBackButton;
    Button clearAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //Buttons
        goBackButton = findViewById(R.id.goBackButton);
        clearAllButton = findViewById(R.id.clearAllButton);

        goBackButton.setOnClickListener(this);
        clearAllButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==goBackButton.getId())
        {
            Intent intent = new Intent(this, GasFinder.class);
            startActivity(intent);
        }
        else if(view.getId()==clearAllButton.getId())
        {

        }
    }
}
