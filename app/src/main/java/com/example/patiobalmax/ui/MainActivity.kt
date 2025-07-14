package com.example.patiobalmax.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patiobalmax.R
import com.example.patiobalmax.adapter.PuestoAdapter
import com.example.patiobalmax.database.AppDatabase
import kotlinx.android.synthetic.main.activity_mapa_estacionamiento.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PuestoAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_estacionamiento)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Configurar RecyclerView
        val usuario = intent.getStringExtra(Constants.EXTRA_USUARIO) ?: "Invitado"
        val permisos = intent.getStringExtra(Constants.EXTRA_PERMISOS) ?: ""

        setupRecyclerView(usuario, permisos)
        setupBotonesAccesoRapido()
    }

    private fun setupRecyclerView(usuario: String, permisos: String) {
        val patioSeleccionado = intent.getIntExtra(Constants.EXTRA_PATIO, 1)

        // Cargar puestos del patio seleccionado desde la BD
        val puestos = getPuestosPorPatio(patioSeleccionado)

        adapter = PuestoAdapter(puestos.toMutableList())
        recyclerViewPuestos.adapter = adapter
        recyclerViewPatos.layoutManager = LinearLayoutManager(this)
    }

    private fun getPuestosPorPatio(patioId: Int): List<EstadoPuesto> {
        return listOf(
            EstadoPuesto(1, "Auto", "AA1122", null, null, "Arrendatario"),
            EstadoPuesto(2, "Camion", "CC3344", "Rampla", "BB1122", "Particular"),
            EstadoPuesto(3, "Van", null, null, null, "Libre")
        )
    }

    private fun setupBotonesAccesoRapido() {
        btnPatio1.setOnClickListener { navegarAPatio(1) }
        btnPatio2.setOnClickListener { navegarAPatio(2) }
        btnPatio3.setOnClickListener { navegarAPatio(3) }
        btnPatio4.setOnClickListener { navegarAPatio(4) }
        btnPatio5.setOnClickListener { navegarAPatio(5) }
        btnPatio6.setOnClickListener { navegarAPatio(6) }
        btnPatio7.setOnClickListener { navegarAPatio(7) }
    }

    private fun navegarAPatio(patioId: Int) {
        val intent = Intent(this, PatioEstacionamiento::class.java).apply {
            putExtra(Constants.EXTRA_PATIO, patioId)
            putExtra(Constants.EXTRA_USUARIO, intent.getStringExtra(Constants.EXTRA_USUARIO))
            putExtra(Constants.EXTRA_PERMISOS, intent.getStringExtra(Constants.EXTRA_PERMISOS))
        }
        startActivity(intent)
    }
}
