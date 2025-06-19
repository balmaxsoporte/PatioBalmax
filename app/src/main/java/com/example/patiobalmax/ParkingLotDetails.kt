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
import com.example.patiobalmax.databinding.ParkingLotDetailsBinding

class ParkingLotDetails : AppCompatActivity() {

    private lateinit var binding: ParkingLotDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ParkingLotDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patioNumber = intent.getIntExtra("patio_number", 1)
        binding.patioTitle.text = "Patio $patioNumber"

        for (i in 1..10) {
            val spotView = layoutInflater.inflate(R.layout.parking_spot_item, null)
            spotView.findViewById<TextView>(R.id.spotNumberTextView).text = "Lugar $i"
            binding.spotsContainer.addView(spotView)
        }

        binding.backButton.setOnClickListener { finish() }
    }
}
