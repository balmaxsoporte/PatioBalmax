package com.example.patiobalmax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patiobalmax.databinding.ItemPuestoBinding
import com.example.patiobalmax.database.entity.Puesto

class PuestoAdapter(val onClick: (Puesto) -> Unit) :
    ListAdapter<Puesto, PuestoAdapter.PuestoViewHolder>(PuestoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuestoViewHolder {
        val binding = ItemPuestoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PuestoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PuestoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PuestoViewHolder(private val binding: ItemPuestoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(puesto: Puesto) {
            binding.tvNumeroPuesto.text = "Puesto ${puesto.numeroPuesto}"
            binding.etPlacaLugar1.setText(puesto.placa1)
            binding.etPlacaLugar2.setText(puesto.placa2)

            when {
                puesto.estado == "Arrendatario" -> binding.root.setBackgroundColor(itemView.resources.getColor(android.R.color.holo_green_light))
                puesto.estado == "Particular" -> binding.root.setBackgroundColor(itemView.resources.getColor(android.R.color.holo_blue_light))
                else -> binding.root.setBackgroundColor(itemView.resources.getColor(android.R.color.white))
            }

            binding.iconoEdit.setOnClickListener {
                onClick(puesto)
            }
        }
    }
}

class PuestoDiffCallback : DiffUtil.ItemCallback<Puesto>() {
    override fun areItemsTheSame(oldItem: Puesto, newItem: Puesto): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Puesto, newItem: Puesto): Boolean =
        oldItem == newItem
}
