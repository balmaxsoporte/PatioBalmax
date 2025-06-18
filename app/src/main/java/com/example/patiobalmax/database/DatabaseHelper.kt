package com.example.patiobalmax.database

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
        val defaultUser = ContentValues().apply {
            put(UserTable.COLUMN_USERNAME, "admin")
            put(UserTable.COLUMN_PASSWORD, "admin") // Mejor cifrar después
            put(UserTable.COLUMN_EDIT_USERS, 1)
            put(UserTable.COLUMN_EDIT_PARKING_LOTS, 1)
            put(UserTable.COLUMN_EDIT_LICENSE_PLATES, 1)
        }
        db?.insert(UserTable.TABLE_NAME, null, defaultUser)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aquí puedes manejar actualizaciones de esquema si cambian las tablas
        db?.execSQL("DROP TABLE IF EXISTS ${UserTable.TABLE_NAME}")
        db?.execSQL("DROP TABLE IF EXISTS ${ParkingSpotTable.TABLE_NAME}")
        db?.execSQL("DROP TABLE IF EXISTS ${LicensePlateTable.TABLE_NAME}")
        onCreate(db)
    }
}
