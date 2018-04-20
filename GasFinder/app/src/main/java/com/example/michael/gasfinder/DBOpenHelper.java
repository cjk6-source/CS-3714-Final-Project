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

    public static final String TABLE_NAME = "gas_history";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STATION_NAME = "station_name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TOTAL_EXPENSE = "total_expense";
    public static final String COLUMN_FUEL_TYPE = "fuel_type";
    public static final String COLUMN_PRICE_PER_GALLON = "price_per_gallon";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_STATION_NAME + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_TOTAL_EXPENSE + " REAL," +
                    COLUMN_FUEL_TYPE + " TEXT," +
                    COLUMN_PRICE_PER_GALLON + " REAL)";

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