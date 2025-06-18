package com.example.patiobalmax.database

object UserTable {
    const val TABLE_NAME = "users"

    const val COLUMN_ID = "id"
    const val COLUMN_USERNAME = "username"
    const val COLUMN_PASSWORD = "password"
    const val COLUMN_EDIT_USERS = "edit_users"
    const val COLUMN_EDIT_PARKING_LOTS = "edit_parking_lots"
    const val COLUMN_EDIT_LICENSE_PLATES = "edit_license_plates"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_USERNAME TEXT UNIQUE NOT NULL,
            $COLUMN_PASSWORD TEXT NOT NULL,
            $COLUMN_EDIT_USERS INTEGER DEFAULT 0,
            $COLUMN_EDIT_PARKING_LOTS INTEGER DEFAULT 0,
            $COLUMN_EDIT_LICENSE_PLATES INTEGER DEFAULT 0
        )
    """
}
