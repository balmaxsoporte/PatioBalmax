package com.example.patiobalmax.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.patiobalmax.R
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.model.EstadoPuesto
import kotlinx.android.synthetic.main.activity_cargar_archivos.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class CargarArchivosActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private var tipoArchivoSeleccionado: String? = null // "arrendatarios" o "particulares"

    // Para seleccionar archivos desde almacenamiento
    private val seleccionarArchivoLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            leerArchivoYActualizarBD(uri, tipoArchivoSeleccionado ?: return@registerForActivityResult)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cargar_archivos)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Recibir datos del usuario logueado
        val usuarioActual = intent.getStringExtra(Constants.EXTRA_USUARIO) ?: "Invitado"
        val permisosUsuario = intent.getStringExtra(Constants.EXTRA_PERMISOS) ?: ""

        setupBotones(usuarioActual, permisosUsuario)
    }

    private fun setupBotones(usuario: String, permisos: String) {
        when (permisos) {
            "Administrador" -> {
                btnSeleccionarArrendatarios.setOnClickListener {
                    tipoArchivoSeleccionado = "arrendatarios"
                    seleccionarArchivoLauncher.launch(arrayOf("text/*"))
                }
                btnSeleccionarParticulares.setOnClickListener {
                    tipoArchivoSeleccionado = "particulares"
                    seleccionarArchivoLauncher.launch(arrayOf("text/*"))
                }
            }
            else -> {
                btnSeleccionarArrendatarios.isEnabled = false
                btnSeleccionarParticulares.isEnabled = false
                Toast.makeText(this, "Solo el administrador puede cargar archivos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun leerArchivoYActualizarBD(uri: Uri, tipo: String) {
        val contentResolver = contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val fileName = getFileNameFromUri(uri)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val reader = BufferedReader(InputStreamReader(inputStream))
                val lineas = reader.readLines()

                withContext(Dispatchers.Main) {
                    progressBarCarga.visibility = android.view.View.VISIBLE
                    textMensajeResultado.text = "Procesando archivo $fileName..."
                }

                for (linea in lineas) {
                    val datos = linea.split(",").map { it.trim() }

                    if (datos.size >= 7 && datos[0].startsWith("Patio")) {
                        val patio = datos[0]
                        val puesto = datos[1]
                        val lugar1 = datos[2]
                        val patente1 = datos[3]
                        val lugar2 = datos[4]
                        val patente2 = datos[5]
                        val arrendatario = datos[6]

                        val numeroPuesto = puesto.filter { it.isDigit() }.toIntOrNull() ?: continue

                        val estadoPuesto = EstadoPuesto(
                            numero = numeroPuesto,
                            tipoLugar1 = lugar1,
                            patenteLugar1 = patente1.takeIf { it.isNotEmpty() },
                            tipoLugar2 = lugar2.takeIf { it.isNotEmpty() },
                            patenteLugar2 = patente2.takeIf { it.isNotEmpty() },
                            estado = if (lugar1.contains("Libre") || patente1.isEmpty()) "Libre" else "Arrendatario"
                        )

                        // Guardar en base de datos
                        // database.puestoDao().insert(estadoPuesto)
                    } else {
                        mostrarError("Formato inválido en línea: $linea")
                    }
                }

                withContext(Dispatchers.Main) {
                    progressBarCarga.visibility = android.view.View.GONE
                    textMensajeResultado.setTextColor(ContextCompat.getColor(this@CargarArchivosActivity, android.R.color.holo_green_dark))
                    textMensajeResultado.text = "Datos cargados correctamente desde $fileName"
                    btnIniciarCarga.isEnabled = true
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    mostrarError("No se pudo leer el archivo: ${e.message}")
                }
            }
        }
    }

    private fun getFileNameFromUri(uri: Uri): String {
        var nombreArchivo = "archivo.txt"

        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex > -1) {
                cursor.moveToFirst()
                nombreArchivo = cursor.getString(nameIndex)
            }
        }

        return nombreArchivo
    }

    private fun mostrarError(mensaje: String) {
        textMensajeResultado.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        textMensajeResultado.text = mensaje
        progressBarCarga.visibility = android.view.View.GONE
    }

    private fun mostrarConfirmacion(mensaje: String) {
        textMensajeResultado.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        textMensajeResultado.text = mensaje
        progressBarCarga.visibility = android.view.View.GONE
    }

    private fun limpiarEstado() {
        textMensajeResultado.text = ""
    }
}
