package com.example.patiobalmax

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityLoginEstacionamientoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast

class LoginEstacionamiento : AppCompatActivity() {

    private lateinit var binding: ActivityLoginEstacionamientoBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar base de datos
        db = AppDatabase.getInstance(applicationContext)

        // Cargar usuarios iniciales si es necesario
        setupUsuariosIniciales()

        // Acción del botón "Iniciar Sesión"
        binding.btnIniciarSesion.setOnClickListener {
            val nombreUsuario = binding.etUsuario.text.toString()
            val contrasena = binding.etContrasena.text.toString()

            if (nombreUsuario.isNotBlank() && contrasena.isNotBlank()) {
                validarCredenciales(nombreUsuario, contrasena)
            } else {
                Toast.makeText(this, "Por favor ingresa usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupUsuariosIniciales() {
        CoroutineScope(Dispatchers.IO).launch {
            val admin = db.usuarioDao().getUsuarioPorNombre("admin")
            val user1 = db.usuarioDao().getUsuarioPorNombre("user1")
            val user2 = db.usuarioDao().getUsuarioPorNombre("user2")

            if (admin == null) {
                db.usuarioDao().insert(
                    Usuario(nombreUsuario = "admin", contrasena = "admin", permisos = "Administrador")
                )
            }

            if (user1 == null) {
                db.usuarioDao().insert(
                    Usuario(nombreUsuario = "user1", contrasena = "12345", permisos = "Editar Patentes")
                )
            }

            if (user2 == null) {
                db.usuarioDao().insert(
                    Usuario(nombreUsuario = "user2", contrasena = "12345", permisos = "Validar Patios y Puestos")
                )
            }
        }
    }

    private fun validarCredenciales(usuario: String, contrasena: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioEncontrado = db.usuarioDao().getUsuarioPorNombre(usuario)

            withContext(Dispatchers.Main) {
                if (usuarioEncontrado != null && usuarioEncontrado.contrasena == contrasena) {
                    val intent = Intent(this@LoginEstacionamiento, MapaEstacionamiento::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginEstacionamiento, R.string.mensaje_error_credenciales, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
