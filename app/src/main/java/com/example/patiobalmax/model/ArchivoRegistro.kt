package com.example.patiobalmax.model

/**
 * Representa un registro proveniente de archivos .txt
 * Ejemplo de línea:
 * Patio 1,Puesto 1,Auto,AA1122,Rampla,BB1122,ING
 */
data class ArchivoRegistro(
    val patio: Int,
    val puesto: Int,
    val detalleLugar1: String,
    val patenteLugar1: String,
    val detalleLugar2: String? = null,
    val patenteLugar2: String? = null,
    val nombreArrendatario: String? = null
)
