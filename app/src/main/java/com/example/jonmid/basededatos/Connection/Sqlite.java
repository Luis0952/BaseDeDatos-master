package com.example.jonmid.basededatos.Connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jonmid.basededatos.Utilities.Utilities;

/**
 * Created by jonmid on 24/10/17.
 */

public class Sqlite extends SQLiteOpenHelper {

    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // crear la estructura de la tabla
        db.execSQL(Utilities.CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // verificar si ya existe una version de la base de datos en el movil
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLA_NAME);
        onCreate(db);
    }
}
