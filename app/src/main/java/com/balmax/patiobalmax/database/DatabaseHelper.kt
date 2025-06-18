package com.balmax.patiobalmax.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        const val DATABASE_NAME = "PatioBalmax.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(UserTable.CREATE_TABLE)
        db?.execSQL(ParkingSpotTable.CREATE_TABLE)
        db?.execSQL(LicensePlateTable.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aquí puedes manejar actualizaciones de esquema si cambian las tablas
        db?.execSQL("DROP TABLE IF EXISTS ${UserTable.TABLE_NAME}")
        db?.execSQL("DROP TABLE IF EXISTS ${ParkingSpotTable.TABLE_NAME}")
        db?.execSQL("DROP TABLE IF EXISTS ${LicensePlateTable.TABLE_NAME}")
        onCreate(db)
    }
}
