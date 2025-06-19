package com.example.patiobalmax

import com.example.patiobalmax.utils.FileUtils
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View

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
