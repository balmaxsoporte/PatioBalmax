package com.example.patiobalmax.database

object LicensePlateTable {
    const val TABLE_NAME = "license_plates"

    const val COLUMN_ID = "id"
    const val COLUMN_PLATE = "plate"
    const val COLUMN_VEHICLE_TYPE_1 = "vehicle_type_1"
    const val COLUMN_PLATE_2 = "plate_2"
    const val COLUMN_VEHICLE_TYPE_2 = "vehicle_type_2"
    const val COLUMN_PATIO_NUMBER = "patio_number"
    const val COLUMN_SPOT_NUMBER = "spot_number"

    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_PLATE TEXT NOT NULL,
            $COLUMN_VEHICLE_TYPE_1 TEXT,
            $COLUMN_PLATE_2 TEXT,
            $COLUMN_VEHICLE_TYPE_2 TEXT,
            $COLUMN_PATIO_NUMBER INTEGER NOT NULL,
            $COLUMN_SPOT_NUMBER INTEGER NOT NULL
        )
    """
}
