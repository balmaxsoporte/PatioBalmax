package com.example.patiobalmax

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
import com.example.patiobalmax.databinding.MapScreenBinding

class MapScreen : AppCompatActivity() {

    private lateinit var binding: MapScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MapScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.patio1Button.setOnClickListener { goToParkingLot(1) }
        binding.patio2Button.setOnClickListener { goToParkingLot(2) }
        binding.patio3Button.setOnClickListener { goToParkingLot(3) }
        binding.patio4Button.setOnClickListener { goToParkingLot(4) }
        binding.patio5Button.setOnClickListener { goToParkingLot(5) }
        binding.patio6Button.setOnClickListener { goToParkingLot(6) }
        binding.patio7Button.setOnClickListener { goToParkingLot(7) }

        binding.userManagementButton.setOnClickListener {
            startActivity(Intent(this, UserManagement::class.java))
        }
    }

    private fun goToParkingLot(patioNumber: Int) {
        val intent = Intent(this, ParkingLotDetails::class.java).apply {
            putExtra("patio_number", patioNumber)
        }
        startActivity(intent)
    }
}
