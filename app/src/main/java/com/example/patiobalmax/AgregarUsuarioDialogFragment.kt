package com.example.patiobalmax

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout

class AgregarUsuarioDialogFragment : DialogFragment() {

    private var onUsuarioCreado: ((String, String, String) -> Unit)? = null
    private lateinit var etNombreUsuario: TextInputLayout
    private lateinit var etContrasena: TextInputLayout
    private lateinit var etPermisos: TextInputLayout

    companion object {
        fun newInstance(onUsuarioCreado: (nombre: String, contrasena: String, permisos: String) -> Unit): AgregarUsuarioDialogFragment {
            val fragment = AgregarUsuarioDialogFragment()
            this.onUsuarioCreado = onUsuarioCreado
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_agregar_usuario, null)

        etNombreUsuario = view.findViewById(R.id.et_nombre_usuario)
        etContrasena = view.findViewById(R.id.et_contrasena)
        etPermisos = view.findViewById(R.id.et_permisos)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle("Agregar Usuario")
            .setPositiveButton("Guardar") { _, _ ->
                val nombre = etNombreUsuario.editText?.text.toString()
                val contrasena = etContrasena.editText?.text.toString()
                val permisos = etPermisos.editText?.text.toString()

                if (nombre.isNotBlank() && contrasena.isNotBlank() && permisos.isNotBlank()) {
                    onUsuarioCreado?.invoke(nombre, contrasena, permisos)
                } else {
                    Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}
