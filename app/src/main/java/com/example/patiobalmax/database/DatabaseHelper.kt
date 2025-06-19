package com.example.patiobalmax.database

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        const val DATABASE_NAME = "patiobalmax.db"
        const val DATABASE_VERSION = 1

        private const val TABLE_USERS = "users"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        private const val CREATE_TABLE_USERS = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USERNAME TEXT PRIMARY KEY,
                $COLUMN_PASSWORD TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USERS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(username: String, password: String): Long {
    val db = writableDatabase
    val values = ContentValues().apply {
        put("username", username)
        put("password", password)
    }
    return db.insert("users", null, values)
}
}
