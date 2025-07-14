package com.example.patiobalmax

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patiobalmax.adapter.UsuarioAdapter
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.database.entity.Usuario
import com.example.patiobalmax.util.Constants
import kotlinx.android.synthetic.main.activity_administrar_usuarios.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdministrarUsuarios : AppCompatActivity() {

    private lateinit var adapter: UsuarioAdapter
    private lateinit var database: AppDatabase
    private lateinit var viewModel: LoginEstacionamiento

    // Para seleccionar archivos .txt
    private val seleccionarArchivoLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            leerArchivoYActualizarBD(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_usuarios)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this)[LoginEstacionamiento::class.java]

        // Recibir datos del usuario logueado
        val usuarioActual = intent.getStringExtra(Constants.EXTRA_USUARIO) ?: "Invitado"
        val permisosUsuarioActual = intent.getStringExtra(Constants.EXTRA_PERMISOS) ?: ""

        setupRecyclerView(usuarioActual, permisosUsuarioActual)
        setupBotones(usuarioActual, permisosUsuarioActual)
    }

    private fun setupRecyclerView(usuarioActual: String, permisos: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = database.usuarioDao().getAllUsuarios()

            withContext(Dispatchers.Main) {
                adapter = UsuarioAdapter(usuarios.toMutableList())
                recyclerViewUsuarios.adapter = adapter
                recyclerViewUsuarios.layoutManager = LinearLayoutManager(this@AdministrarUsuarios)

                // Configurar acciones del adaptador
                adapter.setOnItemClickListener { usuario ->
                    if (usuario.nombre != "admin") {
                        navegarAEditarUsuario(usuario)
                    } else {
                        Toast.makeText(this@AdministrarUsuarios, "No puedes editar al administrador", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupBotones(usuarioActual: String, permisos: String) {
        btnAgregarUsuario.setOnClickListener {
            navegarACrearUsuario()
        }

        when (permisos) {
            "Administrador" -> {
                // Mostrar botón para cargar archivos txt
                btnCargarArchivos.setOnClickListener {
                    seleccionarArchivoLauncher.launch(arrayOf("text/*"))
                }
            }
            else -> {
                btnAgregarUsuario.isEnabled = false
                btnCargarArchivos.visibility = android.view.View.GONE
            }
        }
    }

    private fun navegarACrearUsuario() {
        val intent = Intent(this, EditarUsuarioActivity::class.java)
        startActivity(intent)
    }

    private fun navegarAEditarUsuario(usuario: Usuario) {
        val intent = Intent(this, EditarUsuarioActivity::class.java).apply {
            putExtra(Constants.EXTRA_USUARIO_NOMBRE, usuario.nombre)
            putExtra(Constants.EXTRA_USUARIO_CONTRASENA, usuario.contrasena)
            putExtra(Constants.EXTRA_USUARIO_PERMISOS, usuario.permisos)
        }
        startActivity(intent)
    }

    private fun leerArchivoYActualizarBD(uri: Uri) {
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            cursor.moveToFirst()
            val column = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (column > -1) {
                val nombreArchivo = cursor.getString(column)
                val inputStream = contentResolver.openInputStream(uri)
                val lineas = inputStream?.bufferedReader()?.readLines()

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        for (linea in lineas.orEmpty()) {
                            val datos = linea.split(",")
                            if (datos.size >= 7) {
                                val usuario = datos[5]
                                val contrasena = datos[6]
                                val permisos = "Particular"
                                val nuevoUsuario = Usuario(nombre = usuario, contrasena = contrasena, permisos = permisos)
                                database.usuarioDao().insert(nuevoUsuario)
                            }
                        }
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AdministrarUsuarios, "Usuarios cargados desde $nombreArchivo", Toast.LENGTH_SHORT).show()
                            setupRecyclerView(usuario = "", permisos = "")
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AdministrarUsuarios, "Error al procesar el archivo", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun mostrarConfirmación(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
