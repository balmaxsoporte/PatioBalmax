package com.example.patiobalmax.util

object Constants {
    // Claves para pasar datos entre actividades
    const val EXTRA_USUARIO = "usuario"
    const val EXTRA_PERMISOS = "permisos"
    const val EXTRA_PATIO = "patio"
    const val EXTRA_PUESTO_NUMERO = "puesto_numero"
    const val EXTRA_LUGAR_1 = "lugar1"
    const val EXTRA_PATENTE_LUGAR_1 = "patente_lugar1"
    const val EXTRA_LUGAR_2 = "lugar2"
    const val EXTRA_PATENTE_LUGAR_2 = "patente_lugar2"
    const val EXTRA_ARCHIVOS_RECIENTES = "archivos_recientes"

    // Nombres de archivos predeterminados
    const val ARCHIVO_ARRENDATARIOS = "arrendatarios.txt"
    const val ARCHIVO_PARTICULARES = "particulares.txt"

    // Límites de historial
    const val MAX_HISTORIAL = 5

    // Estados de puestos
    const val ESTADO_LIBRE = "Libre"
    const val ESTADO_ARRIENDO = "Arrendatario"
    const val ESTADO_PARTICULAR = "Particular"

    // Tipos de vehículos y cargas
    val TIPOS_LUGAR1 = listOf(
        "Auto", "Camioneta", "Van", "Camion", "Camion 3/4", "Maquinaria Pesada"
    )

    val TIPOS_LUGAR2 = listOf(
        "Rampla", "Termo", "Cama Baja", "Container", "Tolva", "Estanque", "Carro"
    )
}
