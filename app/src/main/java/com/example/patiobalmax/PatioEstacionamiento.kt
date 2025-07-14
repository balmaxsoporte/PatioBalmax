package com.example.patiobalmax

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.patiobalmax.adapter.PuestoAdapter
import com.example.patiobalmax.database.AppDatabase
import kotlinx.android.synthetic.main.activity_patio_estacionamiento.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatioEstacionamiento : AppCompatActivity() {

    private lateinit var adapter: PuestoAdapter
    private lateinit var database: AppDatabase
    private var patioId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patio_estacionamiento)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Recibir datos del usuario y permisos
        val usuario = intent.getStringExtra(Constants.EXTRA_USUARIO) ?: "Invitado"
        val permisos = intent.getStringExtra(Constants.EXTRA_PERMISOS) ?: ""

        // Configurar título del patio
        patioId = intent.getIntExtra(Constants.EXTRA_PATIO, 1)
        textNombrePatio.text = "Patio $patioId"

        // Configurar RecyclerView
        setupRecyclerView(usuario, permisos)

        // Configurar botones de acceso rápido
        setupBotonesAccesoRapido()

        // Configurar navegación y acciones por permiso
        setupNavegacionPorPermisos(permisos)
    }

    private fun setupRecyclerView(usuario: String, permisos: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val puestos = getPuestosDelPatio(patioId)

            withContext(Dispatchers.Main) {
                adapter = PuestoAdapter(puestos)
                recyclerViewPuestos.adapter = adapter
                recyclerViewPuestos.layoutManager = LinearLayoutManager(this@PatioEstacionamiento)
            }
        }
    }

    private suspend fun getPuestosDelPatio(patioId: Int): List<EstadoPuesto> {
        // Simulación inicial – reemplazar con datos de Room cuando esté lista la implementación
        return listOf(
            EstadoPuesto(1, "Auto", "AA1122", null, null, "Arrendatario"),
            EstadoPuesto(2, "Camion", "CC3344", "Rampla", "BB1122", "Particular"),
            EstadoPuesto(3, "Van", null, null, null, "Libre"),
            EstadoPuesto(4, "Auto", "DD5566", null, null, "Arrendatario"),
            EstadoPuesto(5, "Camion", "EE7788", "Termo", "FF9900", "Particular"),
            EstadoPuesto(6, "Van", null, null, null, "Libre"),
            EstadoPuesto(7, "Camion", "GG1122", "Cama Baja", "HH3344", "Particular"),
            EstadoPuesto(8, "Auto", null, null, null, "Libre"),
            EstadoPuesto(9, "Camion", "II5566", null, null, "Arrendatario"),
            EstadoPuesto(10, "Van", null, "Carro", "JJ7788", "Particular")
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

    private fun setupNavegacionPorPermisos(permisos: String) {
        when (permisos) {
            "Editar Patentes de Patios" -> {
                btnAgregarUsuario.isEnabled = false
                btnAgregarUsuario.isClickable = false
            }
            "Validar Patios y Puestos" -> {
                // Mostrar icono de ticket pero no permitir edición
            }
            else -> {
                // Solo lectura
            }
        }
    }

    // Función para navegar a editar puesto
    private fun navegarAEditarPuesto(puesto: EstadoPuesto, permisos: String) {
        if (permisos == "Editar Patentes de Patios") {
            val intent = Intent(this, EditarPatioEstacionamiento::class.java).apply {
                putExtra(Constants.EXTRA_PUESTO_NUMERO, puesto.numero)
                putExtra(Constants.EXTRA_LUGAR_1, puesto.tipoLugar1)
                putExtra(Constants.EXTRA_PATENTE_LUGAR_1, puesto.patenteLugar1)
                putExtra(Constants.EXTRA_LUGAR_2, puesto.tipoLugar2 ?: "")
                putExtra(Constants.EXTRA_PATENTE_LUGAR_2, puesto.patenteLugar2 ?: "")
                putExtra(Constants.EXTRA_USUARIO, intent.getStringExtra(Constants.EXTRA_USUARIO))
                putExtra(Constants.EXTRA_PERMISOS, intent.getStringExtra(Constants.EXTRA_PERMISOS))
            }
            startActivity(intent)
        }
    }
}
