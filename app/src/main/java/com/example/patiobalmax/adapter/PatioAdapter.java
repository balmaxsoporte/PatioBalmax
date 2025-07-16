package com.example.patiobalmax.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patiobalmax.R;
import com.example.patiobalmax.database.PatioEntity;
import com.example.patiobalmax.ui.MapaEstacionamiento;

import java.util.List;

public class PatioAdapter extends RecyclerView.Adapter<PatioAdapter.PatioViewHolder> {

    private List<PatioEntity> patios;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PatioEntity patio);
    }

    public PatioAdapter(List<PatioEntity> patios, OnItemClickListener listener) {
        this.patios = patios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patio, parent, false);
        return new PatioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatioViewHolder holder, int position) {
        PatioEntity patio = patios.get(position);
        holder.tvNombre.setText(patio.nombre);
        holder.tvNumero.setText("Patio " + (position + 1));
        holder.itemView.setOnClickListener(v -> listener.onItemClick(patio));
    }

    @Override
    public int getItemCount() {
        return patios.size();
    }

    static class PatioViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvNumero;

        public PatioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombrePatio);
            tvNumero = itemView.findViewById(R.id.tvNumeroPatio);
        }
    }
}
