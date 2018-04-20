package com.example.michael.gasfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nicky on 4/19/18.
 */

public class DatabaseManager {

    private SQLiteDatabase database;
    private DBOpenHelper dbOpenHelper;

    public DatabaseManager(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void open() {
        database = dbOpenHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void insertHistoryInfo(String name, String address, String date,
                                  double total, String type, double ppg) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_STATION_NAME, name);
        values.put(DBOpenHelper.COLUMN_ADDRESS, address);
        values.put(DBOpenHelper.COLUMN_DATE, date);
        values.put(DBOpenHelper.COLUMN_TOTAL_EXPENSE, total);
        values.put(DBOpenHelper.COLUMN_FUEL_TYPE, type);
        values.put(DBOpenHelper.COLUMN_PRICE_PER_GALLON, ppg);

        database.insert(DBOpenHelper.TABLE_NAME, null, values);
    }

    public Cursor getAllRecords() {
        return database.query(DBOpenHelper.TABLE_NAME,
                new String[] {
                        DBOpenHelper.COLUMN_ID,
                        DBOpenHelper.COLUMN_STATION_NAME,
                        DBOpenHelper.COLUMN_ADDRESS,
                        DBOpenHelper.COLUMN_DATE,
                        DBOpenHelper.COLUMN_TOTAL_EXPENSE,
                        DBOpenHelper.COLUMN_FUEL_TYPE,
                        DBOpenHelper.COLUMN_PRICE_PER_GALLON
                },
                null, null,
                null, null, DBOpenHelper.COLUMN_ID + " DESC");
    }

    public void deleteAll() {
        if(database.isOpen()) {
            database.execSQL("DELETE FROM " + DBOpenHelper.TABLE_NAME);
        }
    }
}
