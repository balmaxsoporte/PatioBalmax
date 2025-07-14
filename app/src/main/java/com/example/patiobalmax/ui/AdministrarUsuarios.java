package com.example.patiobalmax.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patiobalmax.adapter.UsuarioAdapter;
import com.example.patiobalmax.database.AppDatabase;
import com.example.patiobalmax.database.UsuarioEntity;

import java.util.List;

public class AdministrarUsuarios extends AppCompatActivity {

    private RecyclerView rvUsuarios;
    private TextView tvMensaje;
    private Button btnAgregarUsuario, btnCargarArrendatarios, btnCargarParticulares;
    private UsuarioAdapter usuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_usuarios);

        rvUsuarios = findViewById(R.id.rvUsuarios);
        tvMensaje = findViewById(R.id.tvMensaje);
        btnAgregarUsuario = findViewById(R.id.btnAgregarUsuario);
        btnCargarArrendatarios = findViewById(R.id.btnCargarArrendatarios);
        btnCargarParticulares = findViewById(R.id.btnCargarParticulares);

        AppDatabase db = AppDatabase.getDatabase(this);
        List<UsuarioEntity> usuarios = db.usuarioDao().getAll();

        usuarioAdapter = new UsuarioAdapter(usuarios);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
        rvUsuarios.setAdapter(usuarioAdapter);

        btnAgregarUsuario.setOnClickListener(v -> agregarNuevoUsuario());

        btnCargarArrendatarios.setOnClickListener(v -> cargarArchivo("arrendatarios.txt"));
        btnCargarParticulares.setOnClickListener(v -> cargarArchivo("particulares.txt"));
    }

    private void agregarNuevoUsuario() {
        // Aquí iría lógica para abrir un diálogo o nueva actividad para crear un nuevo usuario
        Toast.makeText(this, "Funcionalidad no implementada aún: Agregar nuevo usuario", Toast.LENGTH_SHORT).show();
    }

    private void cargarArchivo(String nombreArchivo) {
        // Lógica para cargar archivo .txt desde recursos
        Toast.makeText(this, "Cargando archivo: " + nombreArchivo, Toast.LENGTH_SHORT).show();
    }
}
