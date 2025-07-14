package com.example.patiobalmax

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.patiobalmax.adapter.PuestoAdapter
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityMapaEstacionamientoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapaEstacionamiento : AppCompatActivity() {

    private lateinit var binding: ActivityMapaEstacionamientoBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapaEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar base de datos
        db = AppDatabase.getInstance(this)

        setupRecyclerView()
        setupBotonAdminUsuarios()
    }

    private fun setupRecyclerView() {
        CoroutineScope(Dispatchers.IO).launch {
            val patios = db.patioDao().getAllPatios()

            runOnUiThread {
                binding.rvPatios.layoutManager = GridLayoutManager(this@MapaEstacionamiento, 2)
                val adapter = PuestoAdapter(patios) { patio ->
                    val intent = Intent(this@MapaEstacionamiento, PatioEstacionamiento::class.java)
                    intent.putExtra("patio_numero", patio.numeroPatio)
                    startActivity(intent)
                }
                binding.rvPatios.adapter = adapter
            }
        }
    }

    private fun setupBotonAdminUsuarios() {
        binding.btnAdministrarUsuarios.setOnClickListener {
            val intent = Intent(this, AdministrarUsuarios::class.java)
            startActivity(intent)
        }
    }
}
