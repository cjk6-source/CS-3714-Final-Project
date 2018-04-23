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
        values.put(DBOpenHelper.COLUMN_IMAGE_ID, getImageID(name));
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
                        DBOpenHelper.COLUMN_IMAGE_ID,
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

    public int getImageID(String stationName) {
        switch (stationName) {
            case "Arco":
                return R.drawable.arcologo;
            case "BP":
                return R.drawable.bplogo;
            case "Chevron":
                return R.drawable.chevronlogo;
            case "Citgo":
                return R.drawable.citigologo;
            case "Exxon":
                return R.drawable.exxonlogo;
            case "Fast Mart":
                return R.drawable.fasmartlogo;
            case "Hess":
                return R.drawable.hesslogo;
            case "Kroger":
                return R.drawable.krogerlogo;
            case "Liberty":
                return R.drawable.libertylogo;
            case "Marathon":
                return R.drawable.marathonlogo;
            case "Mobil":
                return R.drawable.mobillogo;
            case "7-11":
                return R.drawable.sevenelevenlogo;
            case "Sheetz":
                return R.drawable.sheetzlogo;
            case "Shell":
                return R.drawable.shelllogo;
            case "Sunoco":
                return R.drawable.suncologo;
            case "Texaco":
                return R.drawable.texacologo;
            case "Valero":
                return R.drawable.valerologo;
            default:
                return R.drawable.defaultlogo;
        }
    }
}
