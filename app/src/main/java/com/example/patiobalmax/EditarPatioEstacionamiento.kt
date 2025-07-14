package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.database.AppDatabase
import kotlinx.android.synthetic.main.activity_editar_patio_estacionamiento.*

class EditarPatioEstacionamiento : AppCompatActivity() {

    private var patioId = 1
    private var puestoNumero = 1
    private lateinit var estadoPuesto: EstadoPuesto

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_patio_estacionamiento)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Obtener datos del puesto desde intent
        recibirDatosDelPuesto()

        // Llenar campos con los valores actuales
        llenarCampos()

        // Configurar botón Guardar Cambios
        btnGuardarCambios.setOnClickListener {
            if (validarPatentes()) {
                actualizarPuestoEnBaseDeDatos()
                finish()
            } else {
                mostrarError("Formato de patente inválido")
            }
        }
    }

    private fun recibirDatosDelPuesto() {
        patioId = intent.getIntExtra(Constants.EXTRA_PATIO, 1)
        puestoNumero = intent.getIntExtra(Constants.EXTRA_PUESTO_NUMERO, 1)
        val tipoLugar1 = intent.getStringExtra(Constants.EXTRA_LUGAR_1) ?: "Auto"
        val patenteLugar1 = intent.getStringExtra(Constants.EXTRA_PATENTE_LUGAR_1)
        val tipoLugar2 = intent.getStringExtra(Constants.EXTRA_LUGAR_2) ?: ""
        val patenteLugar2 = intent.getStringExtra(Constants.EXTRA_PATENTE_LUGAR_2)

        estadoPuesto = EstadoPuesto(
            numero = puestoNumero,
            tipoLugar1 = tipoLugar1,
            patenteLugar1 = patenteLugar1,
            tipoLugar2 = tipoLugar2,
            patenteLugar2 = patenteLugar2,
            estado = "Libre" // Esto puede venir también del intent si está ocupado
        )
    }

    private fun llenarCampos() {
        textTituloEdicion.text = "Editar Puesto $puestoNumero"

        // Rellenar spinner lugar1
        when (estadoPuesto.tipoLugar1) {
            "Auto" -> spinnerLugar1.setSelection(0)
            "Camion" -> spinnerLugar1.setSelection(1)
            "Van" -> spinnerLugar1.setSelection(2)
            "Camioneta" -> spinnerLugar1.setSelection(3)
            "Maquinaria Pesada" -> spinnerLugar1.setSelection(4)
            else -> spinnerLugar1.setSelection(0)
        }

        editTextPatente1.setText(estadoPuesto.patenteLugar1 ?: "")

        // Rellenar spinner lugar2
        when (estadoPuesto.tipoLugar2) {
            "Rampla" -> spinnerLugar2.setSelection(0)
            "Termo" -> spinnerLugar2.setSelection(1)
            "Cama Baja" -> spinnerLugar2.setSelection(2)
            "Container" -> spinnerLugar2.setSelection(3)
            "Tolva" -> spinnerLugar2.setSelection(4)
            "Estanque" -> spinnerLugar2.setSelection(5)
            "Carro" -> spinnerLugar2.setSelection(6)
            else -> spinnerLugar2.setSelection(0)
        }

        editTextPatente2.setText(estadoPuesto.patenteLugar2 ?: "")
    }

    private fun validarPatentes(): Boolean {
        val patente1 = editTextPatente1.text.toString()
        val patente2 = editTextPatente2.text.toString()

        // Validar patente1 (opcional si lugar2 está ocupado)
        if (spinnerLugar1.selectedItem.toString() != "Libre" && patente1.isBlank()) return false

        // Validar patente2 solo si lugar2 tiene valor
        if (spinnerLugar2.selectedItem.toString() != "Libre" && patente2.isBlank()) return false

        return true
    }

    private fun actualizarPuestoEnBaseDeDatos() {
        // Aquí puedes implementar la actualización en Room usando DAOs
        // Por simplicidad, simulamos una actualización exitosa
        mostrarConfirmación()
    }

    private fun mostrarConfirmación() {
        Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
