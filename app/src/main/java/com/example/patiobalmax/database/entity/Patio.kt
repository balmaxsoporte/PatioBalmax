package com.example.patiobalmax.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patio")
data class Patio(
    @PrimaryKey val numero: Int,
    val nombre: String
)
