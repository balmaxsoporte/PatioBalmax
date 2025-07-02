package com.example.patiobalmax.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puestos")
data class Puesto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val numeroPuesto: Int,
    val tipoVehiculo1: String,
    val placa1: String,
    val tipoVehiculo2: String,
    val placa2: String,
    val estado: String,
    val patioId: Int
)
