package es.iesoretania.navigationdrawerbbddkotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE empleado (\n" +
                          "id INTEGER PRIMARY KEY,\n" +
                          "nombre TEXT NOT NULL,\n" +
                          "apellido TEXT NOT NULL,\n" +
                          "salario REAL\n" +
                          ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}