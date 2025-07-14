package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.database.entity.Usuario
import com.example.patiobalmax.databinding.ActivityEditarPatioEstacionamientoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPatioEstacionamientoBinding
    private lateinit var database: AppDatabase

    private var usuarioExistente: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPatioEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Obtener datos del usuario (si existe)
        usuarioExistente = intent.getSerializableExtra(Constants.EXTRA_USUARIO) as? Usuario

        if (usuarioExistente != null) {
            binding.editTextPatente1.setText(usuarioExistente!!.nombre)
            binding.editTextPatente2.setText(usuarioExistente!!.contrasena)
            // Aquí podrías cargar spinner de permisos
        }

        binding.btnGuardarCambios.setOnClickListener {
            val nombre = binding.editTextPatente1.text.toString()
            val contrasena = binding.editTextPatente2.text.toString()
            val permisos = "Editar Patentes de Patios" // Puedes usar spinner para esto

            if (nombre.isNotEmpty() && contrasena.isNotEmpty()) {
                val usuario = Usuario(nombre = nombre, contrasena = contrasena, permisos = permisos)

                CoroutineScope(Dispatchers.IO).launch {
                    if (usuarioExistente == null) {
                        database.usuarioDao().insert(usuario)
                    } else {
                        database.usuarioDao().insert(usuario) // Usamos OnConflictStrategy.Replace
                    }

                    withContext(Dispatchers.Main) {
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Nombre y contraseña son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
