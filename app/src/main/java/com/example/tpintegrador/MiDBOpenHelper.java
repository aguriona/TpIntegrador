package com.example.tpintegrador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiDBOpenHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "alojamientoDB";
    private static final int DATABASE_VERSION = 1;

    public MiDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE INMUEBLE (_id integer primary key autoincrement, codigo integer, nombre text, descripcion text, precio integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
