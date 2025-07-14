package com.example.patiobalmax.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey val nombre: String,
    val contrasena: String,
    val permisos: String
)
