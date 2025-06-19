package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.databinding.ParkingSpotDetailsBinding

class ParkingSpotDetails : AppCompatActivity() {

    private lateinit var binding: ParkingSpotDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ParkingSpotDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener { finish() }
        binding.backButton.setOnClickListener { finish() }
    }
}
