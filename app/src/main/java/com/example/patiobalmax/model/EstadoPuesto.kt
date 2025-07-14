package com.example.patiobalmax.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puesto")
data class EstadoPuesto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val numero: Int,
    val tipoLugar1: String,
    val patenteLugar1: String?,
    val tipoLugar2: String?,
    val patenteLugar2: String?,
    val estado: String // Puede ser: Libre, Arrendatario, Particular
)
