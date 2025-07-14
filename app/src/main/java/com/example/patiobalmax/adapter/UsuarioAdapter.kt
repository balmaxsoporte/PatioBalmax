package com.example.patiobalmax.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.patiobalmax.R
import com.example.patiobalmax.model.Usuario

class UsuarioAdapter(
    private val usuarios: List<Usuario>,
    private val onItemClickListener: ((Usuario) -> Unit)? = null
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreUsuario: TextView = itemView.findViewById(R.id.nombreUsuario)
        val textPermisos: TextView = itemView.findViewById(R.id.textPermisos)
        val iconoPerfil: ImageView = itemView.findViewById(R.id.iconoPerfil)
        val iconoEditar: ImageView = itemView.findViewById(R.id.iconoEditar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]

        // Mostrar nombre del usuario
        holder.nombreUsuario.text = usuario.nombre

        // Mostrar permisos
        holder.textPermisos.text = when(usuario.permisos) {
            "Administrador" -> "Administrador"
            "Editar Patentes de Patios" -> "Edición"
            "Validar Patios y Puestos" -> "Validación"
            else -> "Invitado"
        }

        // Establecer icono de perfil
        holder.iconoPerfil.setImageResource(R.drawable.icono_perfil)

        // Solo mostrar botón de edición si no es admin
        if (usuario.nombre == "admin") {
            holder.iconoEditar.visibility = View.GONE
        } else {
            holder.iconoEditar.visibility = View.VISIBLE
        }

        // Acción al hacer clic en el elemento
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(usuario)
        }
    }

    override fun getItemCount(): Int = usuarios.size

    fun setOnItemClickListener(listener: (Usuario) -> Unit) {
        onItemClickListener?.let {
            it.invoke(it)
        }
    }
}
