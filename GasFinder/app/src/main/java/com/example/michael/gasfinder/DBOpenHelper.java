package com.example.michael.gasfinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nicky on 4/18/18.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gasdb";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "gas history";

    public static final String COLUMN_STATION_NAME = "station name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TOTAL_EXPENSE = "total expense";
    public static final String COLUMN_FUEL_TYPE = "fuel type";
    public static final String COLUMN_PRICE_PER_GALLON = "price per gallon";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_STATION_NAME + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_TOTAL_EXPENSE + " FLOAT," +
                    COLUMN_FUEL_TYPE + " TEXT," +
                    COLUMN_PRICE_PER_GALLON + " FLOAT)";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
    }

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}