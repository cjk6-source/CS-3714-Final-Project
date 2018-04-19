package com.example.michael.gasfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener
{

    Button back;
    Button clear_all;
    TextView total;
    ListView history_list;

    /* Adapter for list view */
    /* Database manager */


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        back = findViewById(R.id.back);
        clear_all = findViewById(R.id.clear_all);
        total = findViewById(R.id.total_value);
        history_list = findViewById(R.id.history_list);

        back.setOnClickListener(this);
        clear_all.setOnClickListener(this);

        /* SimpleCursorAdapter */
        /* Database manager */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.clear_all:
                /* Clear the list view */
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /* Close the database */
    }
}
