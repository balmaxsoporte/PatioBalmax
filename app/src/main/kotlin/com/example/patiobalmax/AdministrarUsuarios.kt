package com.example.patiobalmax

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patiobalmax.adapter.UsuarioAdapter
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityAdministrarUsuariosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class AdministrarUsuarios : AppCompatActivity() {

    private lateinit var binding: ActivityAdministrarUsuariosBinding
    private lateinit var adapter: UsuarioAdapter
    private val db by lazy { AppDatabase.getDatabase(this) }

    // Historial de archivos cargados
    private val historialArrendatarios = mutableListOf<String>()
    private val historialParticulares = mutableListOf<String>()

    // Contrato para seleccionar archivo
    private val pickFileLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { fileUri ->
            CoroutineScope(Dispatchers.IO).launch {
                val content = contentResolver.openInputStream(fileUri)?.use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream)).readLines().joinToString("\n")
                }
                withContext(Dispatchers.Main) {
                    if (!content.isNullOrEmpty()) {
                        Toast.makeText(this@AdministrarUsuarios, "Archivo cargado", Toast.LENGTH_SHORT).show()
                        actualizarHistorial(fileUri.lastPathSegment ?: "", content)
                    } else {
                        Toast.makeText(this@AdministrarUsuarios, "Error al leer el archivo", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdministrarUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnAgregarUsuario.setOnClickListener {
            val nombre = binding.etNombreUsuario.text.toString()
            val contrasena = binding.etContrasenaUsuario.text.toString()
            val permisos = binding.spPermisos.selectedItem.toString()

            if (nombre.isNotBlank() && contrasena.isNotBlank()) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.usuarioDao().insert(
                        com.example.patiobalmax.database.entity.Usuario(
                            nombreUsuario = nombre,
                            contrasena = contrasena,
                            permisos = permisos
                        )
                    )
                    withContext(Dispatchers.Main) {
                        binding.etNombreUsuario.text?.clear()
                        binding.etContrasenaUsuario.text?.clear()
                        Toast.makeText(this@AdministrarUsuarios, "Usuario agregado", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCargarArrendatario.setOnClickListener {
            pickFileLauncher.launch("text/*")
        }

        binding.btnCargarParticular.setOnClickListener {
            pickFileLauncher.launch("text/*")
        }

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = UsuarioAdapter { usuario ->
            Toast.makeText(this, "Editar ${usuario.nombreUsuario}", Toast.LENGTH_SHORT).show()
        }

        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = db.usuarioDao().getAll()
            withContext(Dispatchers.Main) {
                binding.rvUsuarios.layoutManager = LinearLayoutManager(this@AdministrarUsuarios)
                binding.rvUsuarios.adapter = adapter
                adapter.submitList(usuarios)
            }
        }
    }

    private fun actualizarHistorial(nombreArchivo: String, contenido: String) {
        if (nombreArchivo.contains("arrendatario", true)) {
            if (historialArrendatarios.size >= 5) {
                historialArrendatarios.removeFirst()
            }
            historialArrendatarios.add(contenido)
        } else if (nombreArchivo.contains("particular", true)) {
            if (historialParticulares.size >= 5) {
                historialParticulares.removeFirst()
            }
            historialParticulares.add(contenido)
        }
        Toast.makeText(this, "Archivo guardado en historial", Toast.LENGTH_SHORT).show()
    }
}
