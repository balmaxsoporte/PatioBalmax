package com.example.patiobalmax

import android.app.Activity
import android.content.Intent
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
    private lateinit var db: AppDatabase
    private lateinit var usuarioAdapter: UsuarioAdapter

    companion object {
        const val EXTRA_USUARIO_ID = "extra_usuario_id"
        const val EXTRA_USUARIO_NOMBRE = "extra_usuario_nombre"
        const val EXTRA_USUARIO_CONTRASENA = "extra_usuario_contrasena"
        const val EXTRA_USUARIO_PERMISOS = "extra_usuario_permisos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdministrarUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        setupRecyclerView()
        setupBotones()
    }

    private fun setupRecyclerView() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = db.usuarioDao().getAllUsuarios()

            runOnUiThread {
                usuarioAdapter = UsuarioAdapter(usuarios) { usuario ->
                    if (usuario.nombreUsuario != "admin") {
                        mostrarDialogoEditarUsuario(usuario)
                    } else {
                        Toast.makeText(this@AdministrarUsuarios, R.string.mensaje_no_puedes_editar_admin, Toast.LENGTH_SHORT).show()
                    }
                }

                binding.rvUsuarios.layoutManager = LinearLayoutManager(this@AdministrarUsuarios)
                binding.rvUsuarios.adapter = usuarioAdapter
            }
        }
    }

    private fun setupBotones() {
        binding.btnAgregarUsuario.setOnClickListener {
            mostrarDialogoAgregarUsuario()
        }

        binding.btnSubirArchivo.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/plain"
            }
            startActivityForResult(intent, REQUEST_CODE_SUBIR_ARCHIVO)
        }
    }

    private fun mostrarDialogoAgregarUsuario() {
        val dialog = AgregarUsuarioDialogFragment.newInstance { nombre, contrasena, permisos ->
            CoroutineScope(Dispatchers.IO).launch {
                db.usuarioDao().insert(
                    Usuario(nombreUsuario = nombre, contrasena = contrasena, permisos = permisos)
                )
                runOnUiThread {
                    Toast.makeText(this@AdministrarUsuarios, R.string.mensaje_usuario_creado, Toast.LENGTH_SHORT).show()
                    setupRecyclerView()
                }
            }
        }
        dialog.show(supportFragmentManager, "AgregarUsuarioDialog")
    }

    private fun mostrarDialogoEditarUsuario(usuario: com.example.patiobalmax.database.entity.Usuario) {
        val dialog = EditarUsuarioDialogFragment.newInstance(usuario) { nuevoNombre, nuevaContrasena, nuevosPermisos ->
            CoroutineScope(Dispatchers.IO).launch {
                db.usuarioDao().actualizarUsuario(nuevoNombre, nuevaContrasena, nuevosPermisos)
                runOnUiThread {
                    Toast.makeText(this@AdministrarUsuarios, R.string.mensaje_usuario_actualizado, Toast.LENGTH_SHORT).show()
                    setupRecyclerView()
                }
            }
        }
        dialog.show(supportFragmentManager, "EditarUsuarioDialog")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SUBIR_ARCHIVO && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val nombreArchivo = getFileName(uri)
                if (nombreArchivo == "arrendatarios.txt" || nombreArchivo == "particulares.txt") {
                    leerYActualizarDatosDesdeArchivo(uri, nombreArchivo)
                } else {
                    Toast.makeText(this, "Archivo no compatible", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getFileName(uri: Uri): String? {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val nameIndex = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            return it.getString(nameIndex)
        }
        return null
    }

    private fun leerYActualizarDatosDesdeArchivo(uri: Uri, nombreArchivo: String) {
        // Aquí iría la lógica para leer el archivo .txt
        Toast.makeText(this, "$nombreArchivo cargado exitosamente", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUEST_CODE_SUBIR_ARCHIVO = 1
    }
}
