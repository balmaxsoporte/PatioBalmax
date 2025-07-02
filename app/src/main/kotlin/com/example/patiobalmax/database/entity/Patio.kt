package com.example.patiobalmax.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patios")
data class Patio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val numeroPatio: Int
)
