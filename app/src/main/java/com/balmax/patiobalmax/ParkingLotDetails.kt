package com.balmax.patiobalmax

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.balmax.patiobalmax.databinding.ParkingLotDetailsBinding

class ParkingLotDetails : AppCompatActivity() {

    private lateinit var binding: ParkingLotDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ParkingLotDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patioNumber = intent.getIntExtra("patio_number", 1)
        binding.patioTitle.text = "Patio $patioNumber"

        // Ejemplo de puestos
        for (i in 1..10) {
            val spotView = layoutInflater.inflate(R.layout.parking_spot_item, null)
            spotView.findViewById<TextView>(R.id.spotNumberTextView).text = "Puesto $i"
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
