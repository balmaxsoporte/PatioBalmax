package com.example.patiobalmax.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patiobalmax.R;
import com.example.patiobalmax.database.PuestoEntity;
import com.example.patiobalmax.model.EstadoPuesto;
import com.example.patiobalmax.ui.EditarPatioEstacionamiento;

import java.util.List;

public class PuestoAdapter extends RecyclerView.Adapter<PuestoAdapter.PuestoViewHolder> {

    private List<PuestoEntity> puestos;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(PuestoEntity puesto);
    }

    public PuestoAdapter(List<PuestoEntity> puestos, OnItemClickListener listener) {
        this.puestos = puestos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PuestoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_puesto, parent, false);
        return new PuestoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PuestoViewHolder holder, int position) {
        PuestoEntity puesto = puestos.get(position);
        holder.tvNumero.setText("Puesto " + (position + 1));
        holder.tvEstado.setText(obtenerEstado(puesto));

        // Asignar color según estado
        EstadoPuesto estado = EstadoPuesto.valueOf(puesto.estado);
        switch (estado) {
            case ARRENDATARIO:
                holder.itemView.setBackgroundColor(Color.parseColor("#4CAF50")); // Verde
                break;
            case PARTICULAR:
                holder.itemView.setBackgroundColor(Color.parseColor("#2196F3")); // Azul
                break;
            default:
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF")); // Blanco
                break;
        }

        // Asignar icono según tipo de vehículo
        String tipoVehiculo = puesto.tipoVehiculoLugar1;
        if (tipoVehiculo.contains("Camión") || tipoVehiculo.contains("Maquinaria")) {
            holder.ivIcono.setImageResource(R.drawable.icono_camion);
        } else {
            holder.ivIcono.setImageResource(R.drawable.icono_auto);
        }

        // Mostrar icono de ticket si tiene permiso
        holder.ivTicket.setVisibility(View.GONE);
        holder.ivEdit.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(puesto));
    }

    private String obtenerEstado(PuestoEntity puesto) {
        return puesto.estado;
    }

    @Override
    public int getItemCount() {
        return puestos.size();
    }

    static class PuestoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumero, tvEstado;
        ImageView ivIcono, ivTicket, ivEdit;

        public PuestoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.tvNumeroPuesto);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            ivIcono = itemView.findViewById(R.id.ivIconoVehiculo);
            ivTicket = itemView.findViewById(R.id.ivTicket);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
