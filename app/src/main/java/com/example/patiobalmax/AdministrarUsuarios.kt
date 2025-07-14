package com.example.patiobalmax

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patiobalmax.adapter.UsuarioAdapter
import com.example.patiobalmax.database.AppDatabase
import com.example.patiobalmax.databinding.ActivityAdministrarUsuariosBinding
import com.example.patiobalmax.model.ArchivoRegistro
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

        private const val REQUEST_CODE_SUBIR_ARCHIVO = 1
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

                binding.recyclerViewUsuarios.layoutManager = LinearLayoutManager(this@AdministrarUsuarios)
                binding.recyclerViewUsuarios.adapter = usuarioAdapter
            }
        }
    }

    private fun setupBotones() {
        binding.btnAgregarUsuario.setOnClickListener {
            mostrarDialogoAgregarUsuario()
        }

        binding.btnCargarArchivos.setOnClickListener {
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
                    com.example.patiobalmax.database.entity.Usuario(nombreUsuario = nombre, contrasena = contrasena, permisos = permisos)
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
            if (nuevoNombre.isNotBlank() && nuevaContrasena.isNotBlank() && nuevosPermisos.isNotBlank()) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.usuarioDao().actualizarUsuario(nuevoNombre, nuevaContrasena, nuevosPermisos)
                    runOnUiThread {
                        Toast.makeText(this@AdministrarUsuarios, R.string.mensaje_usuario_actualizado, Toast.LENGTH_SHORT).show()
                        setupRecyclerView()
                    }
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
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            return cursor.getString(nameIndex)
        }
        return null
    }

    private fun leerYActualizarDatosDesdeArchivo(uri: Uri, nombreArchivo: String) {
        val lineas = FileUtils.leerArchivoDesdeUri(this, uri)
        val registros = if (nombreArchivo == "arrendatarios.txt") {
            FileUtils.procesarArchivoArrendatarios(lineas)
        } else {
            FileUtils.procesarArchivoParticulares(lineas)
        }

        CoroutineScope(Dispatchers.IO).launch {
            registros.forEach { registro ->
                val puesto = db.puestoDao().getPuestosPorPatio(registro.patio)
                    .find { it.numeroPuesto == registro.puesto }

                if (puesto != null) {
                    val puestoActualizado = when (registro.detalleLugar1) {
                        "Camión", "Auto", "Van", "Camioneta", "Moto" -> {
                            puesto.copy(
                                tipoVehiculoLugar1 = registro.detalleLugar1,
                                patenteLugar1 = registro.patenteLugar1,
                                esArrendatario = true,
                                nombreArrendatario = registro.nombreArrendatario
                            )
                        }
                        else -> puesto
                    }
                    db.puestoDao().update(puestoActualizado)
                }
            }

            runOnUiThread {
                Toast.makeText(this@AdministrarUsuarios, R.string.mensaje_archivo_cargado, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
