package com.example.patiobalmax.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patiobalmax.R;
import com.example.patiobalmax.database.UsuarioEntity;
import com.example.patiobalmax.ui.AdministrarUsuarios;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<UsuarioEntity> usuarios;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(UsuarioEntity usuario);
    }

    public UsuarioAdapter(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        UsuarioEntity usuario = usuarios.get(position);
        holder.tvNombre.setText(usuario.nombreUsuario);
        holder.tvPermisos.setText(obtenerPermisos(usuario));
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onItemClick(usuario));
        }
    }

    private String obtenerPermisos(UsuarioEntity usuario) {
        StringBuilder permisos = new StringBuilder();
        if (usuario.permisoAdministrador) permisos.append("Administrador, ");
        if (usuario.permisoEditarPatentes) permisos.append("Editar Patentes, ");
        if (usuario.permisoValidarPuestos) permisos.append("Validar Puestos");
        return permisos.toString();
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvPermisos;
        ImageView ivEdit;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreUsuario);
            tvPermisos = itemView.findViewById(R.id.tvPermisos);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
