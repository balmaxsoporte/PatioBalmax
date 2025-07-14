package com.example.patiobalmax.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.patiobalmax.R
import com.example.patiobalmax.model.EstadoPuesto

class PuestoAdapter(
    private val puestos: List<EstadoPuesto>,
    private val onItemClickListener: ((EstadoPuesto) -> Unit)? = null
) : RecyclerView.Adapter<PuestoAdapter.PuestoViewHolder>() {

    inner class PuestoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPuesto: TextView = itemView.findViewById(R.id.textPuesto)
        val textEstado: TextView = itemView.findViewById(R.id.textEstado)
        val textArrendatario: TextView = itemView.findViewById(R.id.textArrendatario)
        val iconoVehiculo: ImageView = itemView.findViewById(R.id.iconoVehiculo)
        val iconoAccion: ImageView = itemView.findViewById(R.id.iconoAccion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuestoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_puesto, parent, false)
        return PuestoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PuestoViewHolder, position: Int) {
        val puesto = puestos[position]

        // Mostrar número de puesto
        holder.textPuesto.text = "Puesto ${puesto.numero}"

        // Mostrar tipo de lugar1
        holder.textEstado.text = when (puesto.tipoLugar1) {
            "Auto", "Camioneta", "Van" -> "Libre"
            "Camion", "Maquinaria Pesada" -> "Libre"
            else -> "Ocupado - ${puesto.estado}"
        }

        // Mostrar nombre de arrendatario si corresponde
        holder.textArrendatario.text = if (puesto.estado == "Arrendatario" && !puesto.patenteLugar1.isNullOrEmpty()) {
            puesto.patenteLugar1
        } else {
            ""
        }

        // Establecer icono del vehículo
        holder.iconoVehiculo.setImageResource(
            when (puesto.tipoLugar1) {
                "Auto", "Camioneta", "Van" -> R.drawable.icono_auto
                "Camion", "Camion 3/4", "Maquinaria Pesada" -> R.drawable.icono_camion
                else -> R.drawable.icono_ticket
            }
        )

        // Cambiar color de fondo según estado
        holder.itemView.setBackgroundColor(
            when (puesto.estado) {
                "Arrendatario" -> R.color.green
                "Particular" -> R.color.blue
                else -> R.color.white
            }
        )

        // Configurar acción al hacer clic
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(puesto)
        }
    }

    override fun getItemCount(): Int = puestos.size
}
