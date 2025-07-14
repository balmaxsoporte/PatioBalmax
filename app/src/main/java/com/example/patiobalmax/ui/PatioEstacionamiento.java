package com.example.patiobalmax.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patiobalmax.adapter.PuestoAdapter;
import com.example.patiobalmax.database.AppDatabase;
import com.example.patiobalmax.database.PatioEntity;
import com.example.patiobalmax.database.PuestoEntity;

import java.util.List;

public class PatioEstacionamiento extends AppCompatActivity {

    private TextView tvNombrePatio;
    private RecyclerView rvPuestos;
    private PuestoAdapter puestoAdapter;
    private List<PuestoEntity> puestos;
    private PatioEntity patioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patio_estacionamiento);

        tvNombrePatio = findViewById(R.id.tvNombrePatio);
        rvPuestos = findViewById(R.id.rvPuestos);

        // Recuperar el patio seleccionado desde la intención
        if (getIntent().hasExtra("patio")) {
            patioSeleccionado = getIntent().getParcelableExtra("patio");
            tvNombrePatio.setText(patioSeleccionado.getNombre());
        }

        AppDatabase db = AppDatabase.getDatabase(this);
        puestos = db.puestoDao().getAllByPatioId(patioSeleccionado.getId());

        puestoAdapter = new PuestoAdapter(puestos, this::navegarAEditarPuesto);
        rvPuestos.setLayoutManager(new LinearLayoutManager(this));
        rvPuestos.setAdapter(puestoAdapter);
    }

    private void navegarAEditarPuesto(PuestoEntity puesto) {
        Intent intent = new Intent(PatioEstacionamiento.this, EditarPatioEstacionamiento.class);
        intent.putExtra("puesto", puesto);
        startActivity(intent);
    }
}
