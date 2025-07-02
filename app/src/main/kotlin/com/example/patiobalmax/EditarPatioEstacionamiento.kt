package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityEditarPatioEstacionamientoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarPatioEstacionamiento : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPatioEstacionamientoBinding
    private val db by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPatioEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val puestoId = intent.getIntExtra("puesto_id", -1)

        if (puestoId != -1) {
            CoroutineScope(Dispatchers.IO).launch {
                val puesto = db.puestoDao().getById(puestoId)
                runOnUiThread {
                    binding.etPlacaLugar1.setText(puesto.placa1)
                    binding.etPlacaLugar2.setText(puesto.placa2)
                }
            }
        }

        binding.btnGuardar.setOnClickListener {
            val placa1 = binding.etPlacaLugar1.text.toString()
            val placa2 = binding.etPlacaLugar2.text.toString()

            if (puestoId != -1) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.puestoDao().update(placa1, placa2, puestoId)
                }
            }

            finish()
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
}
