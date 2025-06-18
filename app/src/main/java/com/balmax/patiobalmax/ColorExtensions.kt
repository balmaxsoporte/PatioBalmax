package com.balmax.patiobalmax.utils

import android.graphics.Color
import com.balmax.patiobalmax.ParkingData.PlateType

fun getColorForPlateType(type: PlateType): Int {
    return when (type) {
        PlateType.PARTICULAR -> Color.parseColor("#4CAF50") // Verde
        PlateType.ARRENDATARIO -> Color.parseColor("#2196F3") // Azul
        PlateType.NONE -> Color.parseColor("#FFFFFF") // Blanco
    }
}
