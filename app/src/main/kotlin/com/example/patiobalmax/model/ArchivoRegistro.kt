package com.example.patiobalmax.model

data class ArchivoRegistro(
    val patio: String,
    val puesto: String,
    val tipoVehiculo1: String,
    val placa1: String,
    val tipoVehiculo2: String,
    val placa2: String,
    val nombreArrendatario: String? = null
)
