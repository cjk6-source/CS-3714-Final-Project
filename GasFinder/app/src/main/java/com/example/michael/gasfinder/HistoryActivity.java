package com.example.michael.gasfinder;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    Button back;
    Button clear_all;
    TextView total;
    ListView history_list;

    DatabaseManager databaseManager;
    SimpleCursorAdapter adapter;

    static final String[] fromColumns = {
            DBOpenHelper.COLUMN_STATION_NAME,
            DBOpenHelper.COLUMN_TOTAL_EXPENSE,
            DBOpenHelper.COLUMN_ADDRESS,
            DBOpenHelper.COLUMN_FUEL_TYPE,
            DBOpenHelper.COLUMN_DATE,
            DBOpenHelper.COLUMN_PRICE_PER_GALLON
    };

    static final int[] toViews = {
            R.id.station_name,
            R.id.amount_spent,
            R.id.address,
            R.id.type,
            R.id.date,
            R.id.ppg
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        back = findViewById(R.id.back);
        clear_all = findViewById(R.id.clear_all);
        total = findViewById(R.id.total_sum);
        history_list = findViewById(R.id.history_list);

        back.setOnClickListener(this);
        clear_all.setOnClickListener(this);

        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        Cursor cursor = databaseManager.getAllRecords();
        sum(cursor);

        adapter = new SimpleCursorAdapter(this, R.layout.custom_history_row,
                cursor, fromColumns, toViews, 0);
        history_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.clear_all:
                /*databaseManager.insertHistoryInfo("Test Station",
                        "841 Claytor Sq",
                        "05/10/1995",
                        35.5,
                        "Premium",
                        3.4);*/
                databaseManager.deleteAll();
                Cursor cursor = databaseManager.getAllRecords();
                sum(cursor);
                adapter.changeCursor(cursor);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseManager.close();
    }

    public void sum(Cursor cursor) {
        double sum = 0;
        while(cursor.moveToNext()) {
            sum = sum + cursor.getDouble(4);
        }
        total.setText("" + sum);
        cursor.moveToFirst();
    }
}
