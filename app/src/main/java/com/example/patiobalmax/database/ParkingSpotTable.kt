package com.example.patiobalmax.database

object ParkingSpotTable {
    const val TABLE_NAME = "parking_spots"

    const val COLUMN_ID = "id"
    const val COLUMN_PATIO_NUMBER = "patio_number"
    const val COLUMN_SPOT_NUMBER = "spot_number"
    const val COLUMN_OCCUPIED = "occupied"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_PATIO_NUMBER INTEGER NOT NULL,
            $COLUMN_SPOT_NUMBER INTEGER NOT NULL,
            $COLUMN_OCCUPIED INTEGER DEFAULT 0
        )
    """
}
