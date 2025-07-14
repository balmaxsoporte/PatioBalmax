package com.example.patiobalmax.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puesto")
data class Puesto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val patioId: Int,
    val numero: Int,
    val tipoLugar1: String,
    val patenteLugar1: String?,
    val tipoLugar2: String?,
    val patenteLugar2: String?,
    val estado: String
)
