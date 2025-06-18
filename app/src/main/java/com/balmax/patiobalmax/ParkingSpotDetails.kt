package com.example.patiobalmax

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.databinding.ParkingSpotDetailsBinding

class ParkingSpotDetails : AppCompatActivity() {

    private lateinit var binding: ParkingSpotDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ParkingSpotDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patioNumber = intent.getIntExtra("patio_number", 1)
        val spotNumber = intent.getIntExtra("spot_number", 1)

        binding.title.text = "Puesto $spotNumber - Patio $patioNumber"

        // Opciones de vehículos
        val vehicleTypes = listOf(
            "Camión", "Camión 3/4", "Auto", "Camioneta", "Van", "Maquinaria Pesada",
            "Rampla", "Termo", "Cama Baja", "Container", "Tolba", "Estanque", "Carro"
        )

        binding.vehicleType1.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vehicleTypes))
        binding.vehicleType2.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vehicleTypes))

        binding.saveButton.setOnClickListener {
            // Guardar datos del puesto
            finish()
        }

        binding.backButton.setOnClickListener { finish() }
    }
}
