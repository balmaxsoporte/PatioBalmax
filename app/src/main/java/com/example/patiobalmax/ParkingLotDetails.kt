package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.databinding.ParkingLotDetailsBinding
import com.example.patiobalmax.utils.getColorForPlateType

class ParkingLotDetails : AppCompatActivity() {

    private lateinit var binding: ParkingLotDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ParkingLotDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar datos desde archivos TXT
        ParkingData.loadRegistrationData(this)

        val patioNumber = intent.getIntExtra("patio_number", 1)
        binding.patioTitle.text = "Patio $patioNumber"

        // Simular puestos ocupados con patentes
        val demoSpots = mapOf(
            1 to "ABC123",
            2 to "XYZ789",
            3 to "NOP123"
        )

        for (i in 1..10) {
            val spotView = layoutInflater.inflate(R.layout.parking_spot_item, null)
            val spotText = spotView.findViewById<TextView>(R.id.spotNumberTextView)
            val plateText = spotView.findViewById<TextView>(R.id.licensePlateTextView)
            val spotLayout = spotView.findViewById<LinearLayout>(R.id.spotLayout)

            spotText.text = "Puesto $i"

            val plate = demoSpots[i]
            if (plate != null) {
                plateText.text = "Patente: $plate"
                val plateType = ParkingData.getPlateType(plate)
                spotLayout.setBackgroundColor(getColorForPlateType(plateType))
            } else {
                plateText.text = "Libre"
            }

            spotView.setOnClickListener {
                val intent = Intent(this, ParkingSpotDetails::class.java).apply {
                    putExtra("patio_number", patioNumber)
                    putExtra("spot_number", i)
                }
                startActivity(intent)
            }

            binding.spotsContainer.addView(spotView)
        }

        binding.backButton.setOnClickListener { finish() }
    }
}
