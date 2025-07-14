package com.example.patiobalmax

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout

class EditarUsuarioDialogFragment : DialogFragment() {

    private var onUsuarioActualizado: ((String, String, String) -> Unit)? = null
    private lateinit var usuarioOriginal: String

    private lateinit var etNombreUsuario: TextInputLayout
    private lateinit var etContrasena: TextInputLayout
    private lateinit var etPermisos: TextInputLayout

    companion object {
        fun newInstance(
            usuario: com.example.patiobalmax.database.entity.Usuario,
            onUsuarioActualizado: (nuevoNombre: String, nuevaContrasena: String, nuevosPermisos: String) -> Unit
        ): EditarUsuarioDialogFragment {
            val fragment = EditarUsuarioDialogFragment()
            fragment.usuarioOriginal = usuario.nombreUsuario
            fragment.onUsuarioActualizado = onUsuarioActualizado
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_editar_usuario, null)

        etNombreUsuario = view.findViewById(R.id.et_nombre_usuario)
        etContrasena = view.findViewById(R.id.et_contrasena)
        etPermisos = view.findViewById(R.id.et_permisos)

        etNombreUsuario.editText?.setText(usuarioOriginal)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle("Editar Usuario")
            .setPositiveButton("Guardar") { _, _ ->
                val nuevoNombre = etNombreUsuario.editText?.text.toString()
                val nuevaContrasena = etContrasena.editText?.text.toString()
                val nuevosPermisos = etPermisos.editText?.text.toString()

                if (nuevoNombre.isNotBlank() && nuevaContrasena.isNotBlank() && nuevosPermisos.isNotBlank()) {
                    onUsuarioActualizado?.invoke(nuevoNombre, nuevaContrasena, nuevosPermisos)
                } else {
                    Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}
