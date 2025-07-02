package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patiobalmax.adapter.UsuarioAdapter
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityAdministrarUsuariosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdministrarUsuarios : AppCompatActivity() {

    private lateinit var binding: ActivityAdministrarUsuariosBinding
    private lateinit var adapter: UsuarioAdapter
    private val db by lazy { AppDatabase.getDatabase(this) }

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
                    runOnUiThread {
                        binding.etNombreUsuario.text?.clear()
                        binding.etContrasenaUsuario.text?.clear()
                    }
                }
            }
        }

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = UsuarioAdapter { usuario ->
            // Implementar edición o eliminación
        }

        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = db.usuarioDao().getAll()
            runOnUiThread {
                binding.rvUsuarios.layoutManager = LinearLayoutManager(this@AdministrarUsuarios)
                binding.rvUsuarios.adapter = adapter
                adapter.submitList(usuarios)
            }
        }
    }
}
