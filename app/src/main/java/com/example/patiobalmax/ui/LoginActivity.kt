package com.example.patiobalmax.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.patiobalmax.R
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.database.entity.Usuario
import com.example.patiobalmax.model.Constants
import com.example.patiobalmax.util.FileUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginEstacionamiento
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_estacionamiento)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this)[LoginEstacionamiento::class.java]

        // Referencias a vistas
        val editTextUsuario = findViewById<EditText>(R.id.editTextUsuario)
        val editTextContrasena = findViewById<EditText>(R.id.editTextContrasena)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        // Cargar usuarios iniciales si la tabla está vacía
        cargarUsuariosIniciales()

        // Acción del botón de inicio de sesión
        btnIniciarSesion.setOnClickListener {
            val usuario = editTextUsuario.text.toString()
            val contrasena = editTextContrasena.text.toString()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                verificarCredenciales(usuario, contrasena)
            } else {
                Toast.makeText(this, "Por favor ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Carga los usuarios por defecto si la base de datos está vacía
    private fun cargarUsuariosIniciales() {
        CoroutineScope(Dispatchers.IO).launch {
            val count = database.usuarioDao().getUsuario("admin")
            if (count == null) {
                withContext(Dispatchers.Main) {
                    val usuarios = listOf(
                        Usuario(nombre = "admin", contrasena = "admin", permisos = "Administrador"),
                        Usuario(nombre = "user1", contrasena = "12345", permisos = "Editar Patentes de Patios"),
                        Usuario(nombre = "user2", contrasena = "12345", permisos = "Validar Patios y Puestos")
                    )
                    database.usuarioDao().insert(*usuarios.toTypedArray())
                }
            }
        }
    }

    // Verifica si el usuario y contraseña coinciden con la base de datos
    private fun verificarCredenciales(usuario: String, contrasena: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioEncontrado = database.usuarioDao().getUsuario(usuario)
            withContext(Dispatchers.Main) {
                if (usuarioEncontrado != null && usuarioEncontrado.contrasena == contrasena) {
                    // Inicio de sesión exitoso
                    navegarAMain(usuarioEncontrado.nombre, usuarioEncontrado.permisos)
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Navega a MainActivity pasando datos del usuario logueado
    private fun navegarAMain(usuario: String, permisos: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(Constants.EXTRA_USUARIO, usuario)
            putExtra(Constants.EXTRA_PERMISOS, permisos)
        }
        startActivity(intent)
        finish()
    }
}
