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
    private lateinit var db: AppDatabase
    private var numeroPatio: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatioEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener número de patio desde el intent
        numeroPatio = intent.getIntExtra("patio_numero", 1)
        binding.tvTituloDetallePatio.text = getString(R.string.titulo_detalle_patio, numeroPatio)

        // Inicializar base de datos
        db = AppDatabase.getInstance(this)

        setupRecyclerView()
        setupBotonVolver()
    }

    private fun setupRecyclerView() {
        CoroutineScope(Dispatchers.IO).launch {
            val puestos = db.puestoDao().getPuestosPorPatio(numeroPatio)

            runOnUiThread {
                binding.rvPuestos.layoutManager = LinearLayoutManager(this@PatioEstacionamiento)
                val adapter = PuestoAdapter(puestos) { puesto ->
                    val intent = Intent(this@PatioEstacionamiento, EditarPatioEstacionamiento::class.java)
                    intent.putExtra("puesto_id", puesto.id)
                    intent.putExtra("patio_numero", numeroPatio)
                    startActivity(intent)
                }
                binding.rvPuestos.adapter = adapter
            }
        }
    }

    private fun setupBotonVolver() {
        binding.btnVolver.setOnClickListener {
            finish()
        }
    }
}
