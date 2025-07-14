package com.example.patiobalmax.model

/**
 * Representa un registro proveniente de archivos .txt
 * Ejemplo de línea:
 * Patio 1,Puesto 1,Auto,AA1122,Rampla,BB1122,ING
 */
data class ArchivoRegistro(
    val patio: String,
    val puesto: String,
    val lugar1: String,
    val patenteLugar1: String,
    val lugar2: String,
    val patenteLugar2: String,
    val nombreArrendatario: String? = null
)
