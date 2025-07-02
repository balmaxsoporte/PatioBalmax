package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patiobalmax.adapter.PuestoAdapter
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityPatioEstacionamientoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatioEstacionamiento : AppCompatActivity() {

    private lateinit var binding: ActivityPatioEstacionamientoBinding
    private lateinit var adapter: PuestoAdapter
    private val db by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatioEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patioNumero = intent.getIntExtra("patio_numero", 1)
        binding.tvTituloPatio.text = "Patio $patioNumero"

        setupRecyclerView()

        CoroutineScope(Dispatchers.IO).launch {
            val puestos = db.puestoDao().getByPatio(patioNumero)
            runOnUiThread {
                adapter.submitList(puestos)
            }
        }

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = PuestoAdapter { puesto ->
            val intent = Intent(this, EditarPatioEstacionamiento::class.java).apply {
                putExtra("puesto_id", puesto.id)
            }
            startActivity(intent)
        }

        binding.rvPuestos.layoutManager = LinearLayoutManager(this)
        binding.rvPuestos.adapter = adapter
    }
}
