package com.balmax.patiobalmax

import com.balmax.patiobalmax.utils.FileUtils
import android.content.Context

object ParkingData {

    // Almacena patentes de particulares y arrendatarios
    private var registroPlates = setOf<String>()
    private var arrendatarioPlates = setOf<String>()

    fun loadRegistrationData(context: Context) {
        val registroLines = FileUtils.readLinesFromAsset(context, "registro.txt")
        val arrendatarioLines = FileUtils.readLinesFromAsset(context, "arrendatarios.txt")

        registroPlates = FileUtils.getLicensePlatesFromLines(registroLines)
        arrendatarioPlates = FileUtils.getLicensePlatesFromLines(arrendatarioLines)
    }

    fun getPlateType(plate: String): PlateType {
        return when {
            registroPlates.contains(plate.uppercase()) -> PlateType.PARTICULAR
            arrendatarioPlates.contains(plate.uppercase()) -> PlateType.ARRENDATARIO
            else -> PlateType.NONE
        }
    }

    enum class PlateType {
        PARTICULAR,
        ARRENDATARIO,
        NONE
    }
}
