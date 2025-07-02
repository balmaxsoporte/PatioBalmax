package com.example.patiobalmax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patiobalmax.databinding.ItemUsuarioBinding
import com.example.patiobalmax.database.entity.Usuario

class UsuarioAdapter(val onClick: (Usuario) -> Unit) :
    ListAdapter<Usuario, UsuarioAdapter.UsuarioViewHolder>(UsuarioDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val binding = ItemUsuarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsuarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UsuarioViewHolder(private val binding: ItemUsuarioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(usuario: Usuario) {
            binding.tvNombreUsuario.text = usuario.nombreUsuario
            binding.tvPermisos.text = usuario.permisos
        }
    }
}

class UsuarioDiffCallback : DiffUtil.ItemCallback<Usuario>() {
    override fun areItemsTheSame(oldItem: Usuario, newItem: Usuario): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Usuario, newItem: Usuario): Boolean =
        oldItem == newItem
}
