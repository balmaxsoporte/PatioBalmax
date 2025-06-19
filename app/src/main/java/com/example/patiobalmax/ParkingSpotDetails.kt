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
