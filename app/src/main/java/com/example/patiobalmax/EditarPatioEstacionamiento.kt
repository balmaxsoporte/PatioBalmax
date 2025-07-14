package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityEditarPatioEstacionamientoBinding
import com.example.patiobalmax.database.entity.Puesto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarPatioEstacionamiento : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPatioEstacionamientoBinding
    private lateinit var db: AppDatabase
    private var puestoId: Int = 0
    private var numeroPatio: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPatioEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        puestoId = intent.getIntExtra("puesto_id", 0)
        numeroPatio = intent.getIntExtra("patio_numero", 1)

        setupUI()
        setupBotonGuardar()
    }

    private fun setupUI() {
        CoroutineScope(Dispatchers.IO).launch {
            val puestos = db.puestoDao().getPuestosPorPatio(numeroPatio)
            val puesto = puestos.find { it.id == puestoId }

            runOnUiThread {
                if (puesto != null) {
                    binding.tvTituloEditarPuesto.text = getString(R.string.titulo_editar_puesto, puesto.numeroPuesto)

                    binding.etTipoVehiculoLugar1.setText(puesto.tipoVehiculoLugar1)
                    binding.etPatenteLugar1.setText(puesto.patenteLugar1)
                    binding.etTipoVehiculoLugar2.setText(puesto.tipoVehiculoLugar2)
                    binding.etPatenteLugar2.setText(puesto.patenteLugar2)
                }
            }
        }
    }

    private fun setupBotonGuardar() {
        binding.btnGuardarCambios.setOnClickListener {
            val tipoVehiculoLugar1 = binding.etTipoVehiculoLugar1.text.toString()
            val patenteLugar1 = binding.etPatenteLugar1.text.toString()
            val tipoVehiculoLugar2 = binding.etTipoVehiculoLugar2.text.toString()
            val patenteLugar2 = binding.etPatenteLugar2.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val puestos = db.puestoDao().getPuestosPorPatio(numeroPatio)
                val puesto = puestos.find { it.id == puestoId }

                if (puesto != null) {
                    val puestoActualizado = puesto.copy(
                        tipoVehiculoLugar1 = tipoVehiculoLugar1,
                        patenteLugar1 = patenteLugar1,
                        tipoVehiculoLugar2 = tipoVehiculoLugar2,
                        patenteLugar2 = patenteLugar2
                    )
                    db.puestoDao().update(puestoActualizado)
                }

                runOnUiThread {
                    Toast.makeText(this@EditarPatioEstacionamiento, R.string.mensaje_edicion_exitosa, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
