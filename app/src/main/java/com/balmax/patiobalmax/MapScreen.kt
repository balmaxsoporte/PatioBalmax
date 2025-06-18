package com.balmax.patiobalmax

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.balmax.patiobalmax.databinding.MapScreenBinding

class MapScreen : AppCompatActivity() {

    private lateinit var binding: MapScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MapScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            patio1Button.setOnClickListener { goToParkingLot(1) }
            patio2Button.setOnClickListener { goToParkingLot(2) }
            patio3Button.setOnClickListener { goToParkingLot(3) }
            patio4Button.setOnClickListener { goToParkingLot(4) }
            patio5Button.setOnClickListener { goToParkingLot(5) }
            patio6Button.setOnClickListener { goToParkingLot(6) }
            patio7Button.setOnClickListener { goToParkingLot(7) }

            userManagementButton.setOnClickListener {
                startActivity(Intent(this@MapScreen, UserManagement::class.java))
            }
        }
    }

    private fun goToParkingLot(patioNumber: Int) {
        val intent = Intent(this, ParkingLotDetails::class.java).apply {
            putExtra("patio_number", patioNumber)
        }
        startActivity(intent)
    }
}
