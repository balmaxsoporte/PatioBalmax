package com.example.patiobalmax

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.databinding.ActivityMapaEstacionamientoBinding

class MapaEstacionamiento : AppCompatActivity() {

    private lateinit var binding: ActivityMapaEstacionamientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapaEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar botones de patios
        val listener = { patioNumero: Int ->
            val intent = Intent(this, PatioEstacionamiento::class.java).apply {
                putExtra("patio_numero", patioNumero)
            }
            startActivity(intent)
        }

        with(binding) {
            btnPatio1.setOnClickListener { listener(1) }
            btnPatio2.setOnClickListener { listener(2) }
            btnPatio3.setOnClickListener { listener(3) }
            btnPatio4.setOnClickListener { listener(4) }
            btnPatio5.setOnClickListener { listener(5) }
            btnPatio6.setOnClickListener { listener(6) }
            btnPatio7.setOnClickListener { listener(7) }
        }
    }
}
