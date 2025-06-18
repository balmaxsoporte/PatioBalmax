package com.example.patiobalmax.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class UserDao(private val db: SQLiteDatabase) {

    fun insertUser(username: String, password: String, editUsers: Boolean, editParkingLots: Boolean, editLicensePlates: Boolean): Long {
        val values = ContentValues().apply {
            put(UserTable.COLUMN_USERNAME, username)
            put(UserTable.COLUMN_PASSWORD, password)
            put(UserTable.COLUMN_EDIT_USERS, if (editUsers) 1 else 0)
            put(UserTable.COLUMN_EDIT_PARKING_LOTS, if (editParkingLots) 1 else 0)
            put(UserTable.COLUMN_EDIT_LICENSE_PLATES, if (editLicensePlates) 1 else 0)
        }
        return db.insert(UserTable.TABLE_NAME, null, values)
    }

    fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()
        val cursor = db.query(UserTable.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            users.add(parseUser(cursor))
        }
        cursor.close()
        return users
    }

    private fun parseUser(cursor: Cursor): User {
        val idIndex = cursor.getColumnIndex(UserTable.COLUMN_ID)
        val usernameIndex = cursor.getColumnIndex(UserTable.COLUMN_USERNAME)
        val passwordIndex = cursor.getColumnIndex(UserTable.COLUMN_PASSWORD)
        val editUsersIndex = cursor.getColumnIndex(UserTable.COLUMN_EDIT_USERS)
        val editParkingLotsIndex = cursor.getColumnIndex(UserTable.COLUMN_EDIT_PARKING_LOTS)
        val editLicensePlatesIndex = cursor.getColumnIndex(UserTable.COLUMN_EDIT_LICENSE_PLATES)

        return User(
            id = cursor.getLong(idIndex),
            username = cursor.getString(usernameIndex),
            password = cursor.getString(passwordIndex),
            editUsers = cursor.getInt(editUsersIndex) == 1,
            editParkingLots = cursor.getInt(editParkingLotsIndex) == 1,
            editLicensePlates = cursor.getInt(editLicensePlatesIndex) == 1
        )
    }
}

data class User(
    val id: Long,
    val username: String,
    val password: String,
    val editUsers: Boolean,
    val editParkingLots: Boolean,
    val editLicensePlates: Boolean
)
