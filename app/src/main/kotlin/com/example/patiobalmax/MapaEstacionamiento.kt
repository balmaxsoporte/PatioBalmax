package com.example.patiobalmax

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MapaEstacionamiento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_estacionamiento)

        val tvTituloPatio = findViewById<TextView>(R.id.tvTituloPatio)
        tvTituloPatio.text = "Seleccione un Patio"

        findViewById<Button>(R.id.btnPatio1).setOnClickListener {
            val intent = Intent(this, PatioEstacionamiento::class.java).apply {
                putExtra("patio_numero", 1)
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnPatio2).setOnClickListener {
            val intent = Intent(this, PatioEstacionamiento::class.java).apply {
                putExtra("patio_numero", 2)
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAdministrarUsuarios).setOnClickListener {
            startActivity(Intent(this, AdministrarUsuarios::class.java))
        }
    }
}
